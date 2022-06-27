package com.example.mcommerceadminapp.view.products.all_products.view.adapter

import com.example.mcommerceadminapp.pojo.products.Products

interface ProductsCommunicator {
    fun deleteProduct(product:Products)
    fun showDetails(productID:String)
}