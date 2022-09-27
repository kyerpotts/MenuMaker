package com.krkp.menumaker.fragments.specials


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.krkp.menumaker.R
import com.krkp.menumaker.database.entities.Food
import kotlinx.android.synthetic.main.food_item_row.view.*

//
class SpecialsAdapter : RecyclerView.Adapter<SpecialsAdapter.FoodItemViewHolder>() {
    private var specialsList = emptyList<Food>()

    class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        return FoodItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.food_item_row, parent, false)
        )
    }

    @SuppressLint("DiscouragedApi") // Let me know if there is a better way
    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val currentItem = specialsList[position]
        val context: Context = holder.itemView.ivFoodImage.context
        val id =
            context.resources.getIdentifier(currentItem.imgRef, "drawable", context.packageName)
        holder.itemView.tvRestaurant.text = currentItem.restaurantName
        holder.itemView.tvFood.text = currentItem.foodName
        holder.itemView.tvPrice.text = currentItem.price.toString()
        holder.itemView.ivFoodImage.setImageResource(id)
    }

    override fun getItemCount(): Int {
        return specialsList.size
    }

    fun setData(specials: List<Food>) {
        this.specialsList = specials
        notifyDataSetChanged()
    }
}
//    private var specialsList: List<Food>
//) : RecyclerView.Adapter<SpecialsAdapter.SpecialsViewHolder>() {
//
//    inner class SpecialsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val foodImg : ShapeableImageView = itemView.findViewById(R.id.ivFoodImg)
//        val restaurantName : TextView = itemView.findViewById(R.id.tvRestaurant)
//        val foodName : TextView = itemView.findViewById(R.id.tvFood)
//        val price : TextView = itemView.findViewById(R.id.tvPrice)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialsViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_specials, parent,false)
//        return SpecialsViewHolder(view)
//    }
//
//    @SuppressLint("DiscouragedApi") // Let me know if there is a better way
//    override fun onBindViewHolder(holder: SpecialsViewHolder, position: Int) {
//    }
//
//    override fun getItemCount(): Int {
//        return specialsList.size
//    }
//}