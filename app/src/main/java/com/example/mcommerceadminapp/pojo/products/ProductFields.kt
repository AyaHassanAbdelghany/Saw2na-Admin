package com.example.mcommerceadminapp.pojo.products

import com.google.gson.annotations.SerializedName

data class ProductFields (
    @SerializedName("product_type" )
    var productType : String
)