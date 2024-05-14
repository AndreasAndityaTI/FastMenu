package id.ac.ukdw.fastmenu

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tasktrackerapp.R

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_activity)

        //Tampilkan Laman Update dengan Placeholder
        val getDataId = intent.getStringExtra(AMBIL_ID)
        val getDataTitle = intent.getStringExtra(AMBIL_TITLE)
        findViewById<TextView>(R.id.inputTitle).text=getDataTitle
        val getDataDesc= intent.getStringExtra(AMBIL_DESC)
        findViewById<TextView>(R.id.inputDesc).text=getDataDesc





        //Update data pada database
        val submitAddButton: Button = findViewById(R.id.btnUpdate)

        submitAddButton.setOnClickListener {
            val title = findViewById<EditText>(R.id.inputTitle).text.toString()
            val desc = findViewById<EditText>(R.id.inputDesc).text.toString()

            val tanggal = findViewById<EditText>(R.id.dueUpdate).text.toString()


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