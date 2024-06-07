package id.ac.ukdw.fastmenu.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktrackerapp.R
import id.ac.ukdw.fastmenu.adapter.MakananAdapter
import id.ac.ukdw.fastmenu.data.Makanan

class MakananListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makanan_list)

        // Dummy data
        val restaurants = listOf(
            Makanan("Burger", "Lorem ipsum", R.drawable.burger_king),
            Makanan("French Friess", "lorems.", R.drawable.mcdonalds),
            Makanan("Fried Chicken", "chcicken", R.drawable.kfc)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_restaurants)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val restaurantAdapter = MakananAdapter(restaurants, object : MakananAdapter.OnItemClickListener {
            override fun onItemClick(restaurant: Makanan) {
                // Handle item click
                val intent = Intent(this@MakananListActivity, RestaurantDetailActivity::class.java)
                intent.putExtra("restaurant_name", restaurant.name)
                intent.putExtra("restaurant_description", restaurant.description)
                intent.putExtra("restaurant_image", restaurant.imageResId)
                startActivity(intent)
            }
        })
        recyclerView.adapter = restaurantAdapter
    }
}
