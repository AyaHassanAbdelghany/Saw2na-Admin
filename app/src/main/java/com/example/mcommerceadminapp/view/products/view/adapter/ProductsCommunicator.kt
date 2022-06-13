package com.example.mcommerceadminapp.view.products.view.adapter

interface ProductsCommunicator {
    fun setDefaultAddress(addressID:String)
    fun deleteProduct(addressID:String)
}