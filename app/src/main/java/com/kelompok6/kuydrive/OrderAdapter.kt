package com.kelompok6.kuydrive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private val orderList: List<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvService: TextView = itemView.findViewById(R.id.tvService)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        val tvPickUp: TextView = itemView.findViewById(R.id.tvPickUp)
        val tvDestination: TextView = itemView.findViewById(R.id.tvDestination)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.tvService.text = order.serviceName
        holder.tvStatus.text = order.status
        holder.tvPickUp.text = "Dari: ${order.pickUp}"
        holder.tvDestination.text = "Ke: ${order.destination}"
        holder.tvDate.text = order.date
        holder.tvPrice.text = order.price
    }

    override fun getItemCount() = orderList.size
}