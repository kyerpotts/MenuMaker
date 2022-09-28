package com.krkp.menumaker.fragments.menu

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.krkp.menumaker.R
import com.krkp.menumaker.database.entities.Food
import com.krkp.menumaker.database.entities.OrderItem
import kotlinx.android.synthetic.main.food_item_row.view.*

class MenuAdapter(val onClickListener: OnClickListener) : RecyclerView.Adapter<MenuAdapter.MenuItemViewHolder>() {
    private var menuList = emptyList<Food>()

    inner class MenuItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                val foodItem = menuList[adapterPosition]
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        return MenuItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.food_item_row, parent, false)
        )
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val currentItem = menuList[position]
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
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun setData(menu: List<Food>) {
        this.menuList = menu
        notifyDataSetChanged()
    }

}