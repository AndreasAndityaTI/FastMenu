package id.ac.ukdw.fastmenu.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktrackerapp.R
import id.ac.ukdw.fastmenu.adapter.RestaurantAdapter
import id.ac.ukdw.fastmenu.data.Restaurant

class RestaurantListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        // Dummy data
        val restaurants = listOf(
            Restaurant("Burger King", "Every day, more than 11 million guests visit Burger King restaurants around the world. And they do so because our restaurants are known for serving high-quality, great-tasting, and affordable food. Founded in 1954, Burger King is the second largest fast food hamburger chain in the world. The original Home of the Whopper, our commitment to premium ingredients, signature recipes, and family-friendly dining experiences is what has defined our brand for more than 50 successful years.", R.drawable.burger_king),
            Restaurant("McDonald's", "Back in 1954, a man named Ray Kroc discovered a small burger restaurant in California, and wrote the first page of our history. From humble beginnings as a small restaurant, we're proud to have become one of the world's leading food service brands with more than 36,000 restaurants in more than 100 countries.", R.drawable.mcdonalds),
            Restaurant("KFC", "KFC's roots trace back 90 years when Harland Sanders, who operated a service station in Corbin, Kentucky, started cooking for hungry travelers who stopped in for gas. In 1936, Kentucky Governor Ruby Laffoon made Sanders an honorary Kentucky Colonel in recognition of his contributions to the State's cuisine. There are 22,600 KFC outlets in 135 countries around the world. Ninety-nine percent of the units are franchised. KFC's parent company is YUM! Brands, which also owns Taco Bell and Pizza Hut. Worldwide KFC system sales grew 6% last year.", R.drawable.kfc)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_restaurants)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val restaurantAdapter = RestaurantAdapter(restaurants, object : RestaurantAdapter.OnItemClickListener {
            override fun onItemClick(restaurant: Restaurant) {
                // Handle item click
                val intent = Intent(this@RestaurantListActivity, RestaurantDetailActivity::class.java)
                intent.putExtra("restaurant_name", restaurant.name)
                intent.putExtra("restaurant_description", restaurant.description)
                intent.putExtra("restaurant_image", restaurant.imageResId)
                startActivity(intent)
            }
        })
        recyclerView.adapter = restaurantAdapter
    }
}
