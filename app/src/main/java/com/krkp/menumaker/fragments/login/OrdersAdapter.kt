package com.krkp.menumaker.fragments.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krkp.menumaker.R
import com.krkp.menumaker.database.entities.OrderItem
import com.krkp.menumaker.database.entities.Orders
import com.krkp.menumaker.fragments.menu.MenuAdapter
import kotlinx.android.synthetic.main.order_row.view.*

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.OrderItemViewHolder>() {
    private var ordersList = emptyList<Orders>()

    class OrderItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        return OrderItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.order_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        val currentItem = ordersList[position]
        holder.itemView.tvOrderID.text = buildString {
            append("# ")
            append(currentItem.orderID.toString())
        }
        holder.itemView.tvDateTime.text = currentItem.dateTime
        holder.itemView.tvPrice.text = buildString {
            append("$")
            append(currentItem.total.toString())
        }
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    fun setData(orders: List<Orders>) {
        this.ordersList = orders
        notifyDataSetChanged()
    }
}