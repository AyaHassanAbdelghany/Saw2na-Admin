package com.example.mcommerceadminapp.view.inventory.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mcommerceadminapp.databinding.ItemInventoryChildBinding
import com.example.mcommerceadminapp.pojo.products.Products
import com.example.mcommerceadminapp.view.products.all_products.view.adapter.ProductsCommunicator

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