package com.example.mcommerceadminapp.model.remote_source.products

import android.util.Log
import com.example.mcommerceadminapp.pojo.products.Products
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody

interface IProductRemoteSource {


    suspend fun getAllProducts(): ArrayList<Products>

    suspend fun addProduct(requestBody: RequestBody): Products

    suspend fun addProductImage(requestBody: RequestBody, productID: String)

    suspend fun deleteProductByID(productID:String)

    suspend fun setInventoryLevel(requestBody: RequestBody)

    suspend fun addProductImage(productID:String,requestBody: RequestBody)


}