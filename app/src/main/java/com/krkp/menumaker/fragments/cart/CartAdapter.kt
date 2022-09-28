package com.krkp.menumaker.fragments.cart

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Index.Order
import com.krkp.menumaker.R
import com.krkp.menumaker.database.entities.OrderItem
import kotlinx.android.synthetic.main.food_item_row.view.*

class CartAdapter(
    val onClickListener: OnClickListener
) : RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {
    private var cartList = emptyList<OrderItem>()

    inner class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.btnAddToCart.text = buildString {
                append("Remove")
            }

            itemView.btnDecreaseValue.setOnClickListener {
                val orderItem = cartList[adapterPosition]
                var numItems: Int = orderItem.numItems
                numItems--
                val newOrderItem = OrderItem(
                    orderItem.foodName,
                    numItems,
                    orderItem.restaurantName,
                    orderItem.imgRef,
                    orderItem.price,
                    orderItem.description
                )

                onClickListener.onClick(newOrderItem)
            }

            itemView.btnIncreaseValue.setOnClickListener {
                val orderItem = cartList[adapterPosition]
                var numItems: Int = orderItem.numItems
                numItems++
                val newOrderItem = OrderItem(
                    orderItem.foodName,
                    numItems,
                    orderItem.restaurantName,
                    orderItem.imgRef,
                    orderItem.price,
                    orderItem.description
                )

                onClickListener.onClick(newOrderItem)
            }

            itemView.btnAddToCart.setOnClickListener {
                val orderItem = cartList[adapterPosition]
                val numItems = 0
                val newOrderItem = OrderItem(
                    orderItem.foodName,
                    numItems,
                    orderItem.restaurantName,
                    orderItem.imgRef,
                    orderItem.price,
                    orderItem.description
                )

                onClickListener.onClick(newOrderItem)
            }
        }
    }

    class OnClickListener(val clickListener: (orderItem: OrderItem) -> Unit) {
        fun onClick(orderItem: OrderItem) = clickListener(orderItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.food_item_row, parent, false)
        )
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val currentItem = cartList[position]
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
        holder.itemView.tvNumItems.text = currentItem.numItems.toString()
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    fun setData(orderItems: List<OrderItem>) {
        this.cartList = orderItems
        notifyDataSetChanged()
    }
}