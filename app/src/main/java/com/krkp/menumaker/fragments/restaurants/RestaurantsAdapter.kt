package com.krkp.menumaker.fragments.restaurants

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.krkp.menumaker.R
import com.krkp.menumaker.database.entities.Restaurants
import kotlinx.android.synthetic.main.food_item_row.view.*
import kotlinx.android.synthetic.main.restaurant_item_row.view.*
import kotlinx.android.synthetic.main.restaurant_item_row.view.tvRestaurant

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.RestaurantItemViewHolder>() {
    private var restaurantsList = emptyList<Restaurants>()

    class RestaurantItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemViewHolder {
        return RestaurantItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item_row, parent, false)
        )
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: RestaurantItemViewHolder, position: Int) {
        val currentItem = restaurantsList[position]
        val context: Context = holder.itemView.ivRestaurantImage.context
        val id = context.resources.getIdentifier(currentItem.logo, "drawable", context.packageName)
        holder.itemView.tvRestaurant.text = currentItem.restaurantName
        holder.itemView.tvBio.text = currentItem.bio
        holder.itemView.ivRestaurantImage.setImageResource(id)

        // Set ViewHolder to be clickable and navigate to MenuFragment
        holder.itemView.btnRestaurantSelect.setOnClickListener {
            val action = RestaurantsFragmentDirections.actionRestaurantsFragmentToMenuFragment(currentItem.restaurantName)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return restaurantsList.size
    }

    fun setData(restaurants: List<Restaurants>) {
        this.restaurantsList = restaurants
        notifyDataSetChanged()
    }


}