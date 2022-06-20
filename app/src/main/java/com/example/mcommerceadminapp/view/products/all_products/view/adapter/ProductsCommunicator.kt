package com.example.mcommerceadminapp.view.products.all_products.view.adapter

interface ProductsCommunicator {
    fun deleteProduct(addressID:String)
    fun showDetails(productID:String)
}