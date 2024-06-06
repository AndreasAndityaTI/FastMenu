package id.ac.ukdw.fastmenu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktrackerapp.R
import id.ac.ukdw.fastmenu.data.Restaurant

class RestaurantAdapter(private val restaurantList: List<Restaurant>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(restaurant: Restaurant)
    }

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantName: TextView = itemView.findViewById(R.id.tv_restaurant_name)
        val restaurantDescription: TextView = itemView.findViewById(R.id.tv_restaurant_description)
        val restaurantImage: ImageView = itemView.findViewById(R.id.iv_restaurant)

        fun bind(restaurant: Restaurant) {
            restaurantName.text = restaurant.name
            restaurantDescription.text = restaurant.description
            restaurantImage.setImageResource(restaurant.imageResId)
            itemView.setOnClickListener {
                itemClickListener.onItemClick(restaurant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurantList[position])
    }

    override fun getItemCount() = restaurantList.size
}
