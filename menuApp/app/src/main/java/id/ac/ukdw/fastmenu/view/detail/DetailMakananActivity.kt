package id.ac.ukdw.fastmenu.view.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.ac.ukdw.fastmenu.data.remote.ApiConfig
import id.ac.ukdw.fastmenu.data.repository.MakananRepository
import id.ac.ukdw.fastmenu.data.response.DetailMakananResponse
import id.ac.ukdw.fastmenu.view.SearchMakananViewModelFactory
import com.example.tasktrackerapp.databinding.ActivityDetailMakananBinding

class DetailMakananActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMakananBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMakananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiConfig.getApiService()
        val makananRepository = MakananRepository(apiService)

        val viewModelFactory = SearchMakananViewModelFactory(makananRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailMakananViewModel::class.java)

        val id = intent.getStringExtra(EXTRA_ID) ?: ""
        Log.d("Checked ID", id)


        binding.appBar.setNavigationOnClickListener{
            finish()
        }
        showLoading(true)
        viewModel.getDetailMakanan(id)
        viewModel.detailMakanan.observe(this) { makanan ->
            setDetailMakanan(makanan)
            showLoading(false)
        }
    }

    private fun setDetailMakanan(makanan: DetailMakananResponse?){
        Glide
            .with(this)
            .load(makanan?.imageUrl)
            .into(binding.ivDetail)

        binding.appBar.title = makanan?.name
        binding.tvDeskripsiDetail.text = makanan?.informasi
        binding.tvHargaDetail.text = makanan?.kisaranHarga
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_ID = "ID"
    }

}