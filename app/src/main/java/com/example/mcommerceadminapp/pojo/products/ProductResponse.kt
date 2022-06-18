package com.example.mcommerceadminapp.pojo.products

import com.example.mcommerceadminapp.pojo.products.Products
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products" )
    var products : ArrayList<Products>
)
