package com.krkp.menumaker.fragments.specials


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krkp.menumaker.R
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.OrderItem
import kotlinx.android.synthetic.main.food_item_row.view.*

// Adapter for the recyclerview in the Specials Fragment
class SpecialsAdapter(val onClickListener: OnClickListener) :
    RecyclerView.Adapter<SpecialsAdapter.FoodItemViewHolder>() {
    private var specialsList = emptyList<Food>()

    // FoodItemViewHolder initialises the onClickListeners to allow the adapter to retrieve information
    inner class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.btnDecreaseValue.setOnClickListener {
                var numItems: Int = itemView.tvNumItems.text.toString().toInt()
                if (numItems > 0) {
                    numItems--
                    itemView.tvNumItems.text = numItems.toString()
                }
            }
            itemView.btnIncreaseValue.setOnClickListener {
                var numItems: Int = itemView.tvNumItems.text.toString().toInt()
                numItems++
                itemView.tvNumItems.text = numItems.toString()
            }

            itemView.btnAddToCart.setOnClickListener {
                // Retrieve food item and convert to order to be inserted to Cart Database
                val foodItem = specialsList[adapterPosition]
                val orderItem = OrderItem(
                    foodItem.foodName,
                    itemView.tvNumItems.text.toString().toInt(),
                    foodItem.restaurantName,
                    foodItem.imgRef,
                    foodItem.price,
                    foodItem.description
                )

                onClickListener.onClick(orderItem)
            }
        }
    }

    class OnClickListener(val clickListener: (orderItem: OrderItem) -> Unit) {
        fun onClick(orderItem: OrderItem) = clickListener(orderItem)
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
        holder.itemView.tvPrice.text = buildString {
            append("$")
            append(currentItem.price)
        }
        holder.itemView.ivFoodImage.setImageResource(id)
        holder.itemView.tvNumItems.text = "0"
    }

    override fun getItemCount(): Int {
        return specialsList.size
    }

    fun setData(specials: List<Food>) {
        this.specialsList = specials
        notifyDataSetChanged()
    }

}
