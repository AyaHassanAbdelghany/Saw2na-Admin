package com.example.mcommerceadminapp.view.inventory.view.adapter

interface InventoryCommunicator {
    fun setInventoryLevel(inventoryID:Long,amount:Int)
    fun deleteProduct(addressID:String)
}