package id.ac.ukdw.fastmenu.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tasktrackerapp.R

class MakananDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_makanan)

        val name = intent.getStringExtra("restaurant_name")
        val description = intent.getStringExtra("restaurant_description")
        val imageResId = intent.getIntExtra("restaurant_image", 0)

        findViewById<TextView>(R.id.iv_makanan).text = name
        findViewById<TextView>(R.id.tv_restaurant_description_detail).text = description
        findViewById<ImageView>(R.id.iv_restaurant_detail).setImageResource(imageResId)
    }
}
