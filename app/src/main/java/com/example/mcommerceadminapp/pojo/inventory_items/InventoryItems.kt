package com.example.example

import com.google.gson.annotations.SerializedName


data class InventoryItems  (

  @SerializedName("inventory_items" ) var inventoryItems : ArrayList<InventoryItem> = arrayListOf()

)