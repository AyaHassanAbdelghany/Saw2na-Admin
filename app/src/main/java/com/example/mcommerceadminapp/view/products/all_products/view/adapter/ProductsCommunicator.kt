package com.example.mcommerceadminapp.view.products.all_products.view.adapter

interface ProductsCommunicator {
    fun setDefaultAddress(addressID:String)
    fun deleteProduct(addressID:String)
    fun showDetails(productID:String)
}