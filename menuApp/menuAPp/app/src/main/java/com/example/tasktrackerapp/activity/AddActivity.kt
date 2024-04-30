package com.example.tasktrackerapp.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tasktrackerapp.R


class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


        val submitAddButton: Button = findViewById(R.id.submitAddButton)

        submitAddButton.setOnClickListener {


            // Check if all fields are filled

        }

    }

//    private fun showAlert(message: String) {

        fun showAlert(message: String) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                    if (message == "Data berhasil disimpan") {
                        // Redirect ke BerandaActivity jika data berhasil disimpan
                        startActivity(Intent(this,BerandaActivity::class.java))
                        finish() // Menutup AddActivity
                    }
                }
            val alert = builder.create()
            alert.show()
        }
    }
