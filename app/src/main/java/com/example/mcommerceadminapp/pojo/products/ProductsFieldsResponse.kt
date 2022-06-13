package com.example.mcommerceadminapp.pojo.products

import com.example.mcommerceadminapp.pojo.products.ProductFields
import com.google.gson.annotations.SerializedName

data class ProductsFieldsResponse(
    @SerializedName("products" )
    var products : HashSet<ProductFields>
    )