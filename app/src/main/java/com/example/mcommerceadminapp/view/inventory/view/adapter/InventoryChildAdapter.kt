package com.example.mcommerceadminapp.view.inventory.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mcommerceadminapp.databinding.ItemInventoryChildBinding
import com.example.mcommerceadminapp.pojo.products.Products

class InventoryChildAdapter (var context: Context, private var listener: InventoryCommunicator) :
    RecyclerView.Adapter<InventoryChildAdapter.ViewHolder>() {
    private var productsList: Products = Products()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInventoryChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = productsList.variants[position]
        holder.binding.apply {
            titleTextView.text = currentItem.title
            quantityTextView.setText(currentItem.inventoryQuantity.toString())
            vendorTextView.text = currentItem.inventoryManagement
        }

        holder.binding.plusBt.setOnClickListener {
            holder.binding.quantityTextView.isEnabled = true
            holder.binding.plusBt.visibility = View.INVISIBLE
            holder.binding.confirmBt.visibility = View.VISIBLE
        }

        holder.binding.confirmBt.setOnClickListener {
            holder.binding.quantityTextView.isEnabled = false
            holder.binding.plusBt.visibility = View.VISIBLE
            holder.binding.confirmBt.visibility = View.INVISIBLE

            listener.setInventoryLevel(currentItem.inventoryItemId!!,holder.binding.quantityTextView.text.toString().toInt())
        }

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
          return productsList.variants.size
    }

    fun setData(productsList: Products) {
        this.productsList = productsList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemInventoryChildBinding) : RecyclerView.ViewHolder(binding.root)

}