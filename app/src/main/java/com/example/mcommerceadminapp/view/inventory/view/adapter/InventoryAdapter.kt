package com.example.mcommerceadminapp.view.inventory.view.adapter

import android.animation.LayoutTransition
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.bumptech.glide.Glide
import com.example.mcommerceadminapp.databinding.ItemInventoryBinding
import com.example.mcommerceadminapp.pojo.products.Products


class InventoryAdapter (var context: Context, private var listener: InventoryCommunicator) :
    RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {
    private var productsList: ArrayList<Products> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInventoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = productsList[position]
        holder.binding.apply {
            titleTextView.text = currentItem.title
            vendorTextView.text = currentItem.vendor
            variantsTextView.text = currentItem.variants.size.toString()
            Glide.with(context)
                .load(currentItem.image?.src)
                .into(productImageView)
        }

        val llm = LinearLayoutManager(context)
        val adapter = InventoryChildAdapter(context,listener)
        llm.orientation = LinearLayoutManager.VERTICAL
        holder.binding.inventoryChildRecycleView.layoutManager = llm
        adapter.setData(currentItem)


        holder.itemView.setOnClickListener {
            if (holder.binding.inventoryChildRecycleView.adapter == null )
                holder.binding.inventoryChildRecycleView.adapter = adapter
            else
                holder.binding.inventoryChildRecycleView.adapter = null
            TransitionManager.beginDelayedTransition(holder.binding.root, AutoTransition())

        }
    }

    override fun getItemCount(): Int {
        return productsList.count()
    }

    fun setData(productsList: ArrayList<Products>) {
        this.productsList = productsList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemInventoryBinding) : RecyclerView.ViewHolder(binding.root)

}