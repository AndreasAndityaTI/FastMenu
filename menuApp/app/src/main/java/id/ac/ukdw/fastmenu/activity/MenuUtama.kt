package id.ac.ukdw.fastmenu


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import id.ac.ukdw.menuapp.R


class MenuUtama : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cardView1 = findViewById<CardView>(R.id.cardView1)
        val cardView2 = findViewById<CardView>(R.id.cardView2)
        cardView1.setOnClickListener {
            // Handle click for cardView1
        }

        cardView2.setOnClickListener {
            // Handle click for cardView2
            navigateToBerandaActivity()
        }
    }
    private fun navigateToBerandaActivity() {
        val intent = Intent(this, BerandaActivity::class.java)
        startActivity(intent)
    }
}