package com.example.mcommerceadminapp.view.products.all_products.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mcommerceadminapp.databinding.ItemProductsBinding
import com.example.mcommerceadminapp.pojo.products.Products
import com.google.gson.Gson


class ProductsAdapter(var context: Context, private var listener: ProductsCommunicator) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private var productsList: ArrayList<Products> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = productsList[position]
        holder.binding.apply {
            titleTextView.text = currentItem.title
            vendorTextView.text = currentItem.vendor
            quantityTextView.text = currentItem.variants.size.toString()
            Glide.with(context)
                .load(currentItem.image?.src)
                .into(productImageView)
        }

        holder.binding.deleteAddress.setOnClickListener {
            listener.deleteProduct(currentItem.id.toString())
            productsList.remove(currentItem)
            notifyDataSetChanged()
        }
        holder.itemView.setOnClickListener {
            listener.showDetails(Gson().toJson(currentItem))
        }
    }

    override fun getItemCount(): Int {
        return productsList.count()
    }

    fun setData(productsList: ArrayList<Products>) {
        this.productsList = productsList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemProductsBinding) : RecyclerView.ViewHolder(binding.root)

}