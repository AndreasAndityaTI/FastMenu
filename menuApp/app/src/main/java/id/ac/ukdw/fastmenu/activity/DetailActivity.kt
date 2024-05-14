package id.ac.ukdw.fastmenu.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tasktrackerapp.R
import id.ac.ukdw.fastmenu.BerandaActivity
import id.ac.ukdw.fastmenu.UpdateActivity


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getDataId = intent.getStringExtra(AMBIL_ID)

        val getDataTitle = intent.getStringExtra(AMBIL_TITLE)
        findViewById<TextView>(R.id.tittle).text=getDataTitle

        val getDataDesc= intent.getStringExtra(AMBIL_DESC)
        findViewById<TextView>(R.id.shipper_field).text=getDataDesc



        val  getDatatgl= intent.getStringExtra(AMBIL_TANGGAL)
        findViewById<TextView>(R.id.textView2).text=getDatatgl

        supportActionBar?.title="DETAIL TASK - $getDataTitle"


        val ButtonDelete: Button = findViewById(R.id.deletebutton)
        ButtonDelete.setOnClickListener{
            deleteTask()
        }
        val ButtonUpdate: Button = findViewById(R.id.btnUpdate)
        ButtonUpdate.setOnClickListener{
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra(UpdateActivity.AMBIL_ID, getDataId)
            intent.putExtra(UpdateActivity.AMBIL_TITLE, getDataTitle)
            intent.putExtra(UpdateActivity.AMBIL_DESC, getDataDesc)

            intent.putExtra(UpdateActivity.AMBIL_TANGGAL, getDatatgl)


            startActivity(intent)
        }
    }

    private fun deleteTask(){

        val getDataId = intent.getStringExtra(AMBIL_ID)
        AlertDialog.Builder(this).apply {
            setTitle("Hapus")
            setMessage("Apakah anda ingin mengahapus tugas?")
            setPositiveButton("Hapus") { _, _ ->
//                Toast.makeText(this, "Berhasil hapus", Toast.LENGTH_SHORT).show()
                Log.e("detail:","berhasil hapus")
                startActivity(Intent(this@DetailActivity, BerandaActivity::class.java))
            }
            setNegativeButton("Cancel") { _, _ ->

            }
            create()
            show()
        }
    }
    companion object {
        const val AMBIL_ID = "id"
        const val AMBIL_TITLE = "TITLE"
        const val AMBIL_DESC = "DESC"
        const val AMBIL_PRIORTY = "PRIORTY"
        const val AMBIL_STATUS = "STATUS"
        const val AMBIL_TANGGAL = "TANGGAL"

    }
}