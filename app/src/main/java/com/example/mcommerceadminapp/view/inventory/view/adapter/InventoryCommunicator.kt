package com.example.mcommerceadminapp.view.inventory.view.adapter

interface InventoryCommunicator {
    fun setDefaultAddress(addressID:String)
    fun deleteProduct(addressID:String)
}