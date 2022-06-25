package com.example.mcommerceadminapp.model.remote_source.products

import android.util.Log
import com.example.mcommerceadminapp.network.ShopifyRetrofitHelper
import com.example.mcommerceadminapp.network.products.ProductsService
import com.example.mcommerceadminapp.pojo.products.Products
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody

class ProductsRemoteSource private constructor() :IProductRemoteSource{

    private val gson = Gson()
    private val api: ProductsService =
        ShopifyRetrofitHelper.getInstance().create(ProductsService::class.java)

    companion object {
        private var remoteSource: ProductsRemoteSource? = null
        fun getInstance(): ProductsRemoteSource {
            return remoteSource ?: ProductsRemoteSource()
        }
    }


    override suspend fun getAllProducts(): ArrayList<Products> {
        val res = api.getAllProducts()
        Log.d("ProductsRemoteSource", "getAllProducts:  $res")
        return gson.fromJson(
            res.body()!!.get("products") as JsonArray,
            object : TypeToken<ArrayList<Products>>() {}.type
        )
    }

   override suspend fun addProduct(requestBody: RequestBody): Products {
        val res = api.addProducts(requestBody)
        Log.d("ProductsRemoteSource", "addProduct:  $res")
        return gson.fromJson(
            res.body()!!.get("product") as JsonObject,
            object : TypeToken<Products>() {}.type
        )
    }

    override suspend fun addProductImage(requestBody: RequestBody, productID: String) {
        api.addProductImage(requestBody, productID)
    }

    override suspend fun deleteProductByID(productID:String){
        val res = api.deleteProductByID(productID)
        Log.d("ProductsRemoteSource", "deleteProductByID:  $res")

    }

    override suspend fun setInventoryLevel(requestBody: RequestBody){
        val res = api.setInventoryLevel(requestBody)
        Log.d("ProductsRemoteSource", "setInventoryLevel:  $res")

    }

    override suspend fun addProductImage(productID: String, requestBody: RequestBody) {
        val res = api.addProductImage(productID,requestBody)
        Log.d("ProductsRemoteSource", "addProductImage:  $res")
    }

}