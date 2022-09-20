package com.krkp.menumaker


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.krkp.menumaker.database.entities.Food

class SpecialsAdapter(
    private var specialsList: List<Food>
) : RecyclerView.Adapter<SpecialsAdapter.SpecialsViewHolder>() {

    inner class SpecialsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foodImg : ShapeableImageView = itemView.findViewById(R.id.ivFoodImg)
        val restaurantName : TextView = itemView.findViewById(R.id.tvRestaurant)
        val foodName : TextView = itemView.findViewById(R.id.tvFood)
        val price : TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_specials, parent,false)
        return SpecialsViewHolder(view)
    }

    @SuppressLint("DiscouragedApi") // Let me know if there is a better way
    override fun onBindViewHolder(holder: SpecialsViewHolder, position: Int) {
        val currentItem = specialsList[position]
        val context: Context = holder.foodImg.context
        val id = context.resources.getIdentifier(currentItem.imgRef, "drawable", context.packageName);

        holder.foodImg.setImageResource(id)
        holder.restaurantName.text = currentItem.restaurantName
        holder.foodName.text = currentItem.foodName
        holder.price.text = currentItem.price.toString()
    }

    override fun getItemCount(): Int {
        return specialsList.size
    }
}