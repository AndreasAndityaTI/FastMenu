package id.ac.ukdw.fastmenu.view.navigation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.tasktrackerapp.databinding.FragmentScanBinding
import id.ac.ukdw.fastmenu.view.camera.getImageUri
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import id.ac.ukdw.fastmenu.view.guide.GuideActivity
import org.tensorflow.lite.DataType

class ScanFragment : Fragment() {
    private lateinit var binding: FragmentScanBinding
    private lateinit var tflite: Interpreter
    private val labels = listOf("burger", "french fries")
    private val imageSize = 224

    private var currentImageUri: Uri? = null

    private val launcherGallery =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                currentImageUri = uri
                showImage()
            } else {
                showToast("No Media Selected")
            }
        }

    private val launcherIntentCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                showImage()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        try {
            tflite = Interpreter(FileUtil.loadMappedFile(requireContext(), "model.tflite"))
        } catch (e: Exception) {
            showToast("Error loading model: ${e.message}")
            Log.e("ScanFragment", "Error loading model", e)
        }

        binding.galleryButton.setOnClickListener { startGallery() }
        binding.cameraButton.setOnClickListener { startCamera() }
        binding.scanButton.setOnClickListener {
            if (binding.ivScanPhoto.drawable != null) {
                uploadImage()
            } else {
                showToast("Silahkan Masukkan Foto untuk di scan")
            }
        }

        binding.tvGuideScan.setOnClickListener {
            val intentGuide = Intent(requireContext(), GuideActivity::class.java)
            startActivity(intentGuide)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tflite.close()
    }

    private fun startGallery() {
        launcherGallery.launch("image/*")
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri)
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivScanPhoto.setImageURI(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            try {
                val bitmap = BitmapFactory.decodeStream(requireContext().contentResolver.openInputStream(uri))
                val scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, true)
                val byteBuffer = convertBitmapToByteBuffer(scaledBitmap)
                val result = classifyImage(byteBuffer)
                showResult(result)
            } catch (e: Exception) {
                showToast("Error processing image: ${e.message}")
                Log.e("ScanFragment", "Error processing image", e)
            }
        } ?: showToast("Please select an image first.")
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(imageSize * imageSize)
        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        for (pixelValue in intValues) {
            byteBuffer.putFloat(((pixelValue shr 16) and 0xFF) / 255.0f)
            byteBuffer.putFloat(((pixelValue shr 8) and 0xFF) / 255.0f)
            byteBuffer.putFloat((pixelValue and 0xFF) / 255.0f)
        }
        return byteBuffer
    }

    private fun classifyImage(byteBuffer: ByteBuffer): String {
        val outputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, labels.size), DataType.FLOAT32)
        tflite.run(byteBuffer, outputBuffer.buffer.rewind())
        val output = outputBuffer.floatArray
        val maxIndex = output.indices.maxByOrNull { output[it] } ?: -1
        return labels.getOrNull(maxIndex) ?: "Unknown"
    }

    private fun showResult(result: String) {
        binding.resultTextView.text = "Predicted: $result"
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}
