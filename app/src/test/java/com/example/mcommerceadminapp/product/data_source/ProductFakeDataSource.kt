package com.example.mcommerceadminapp.product.data_source

import com.example.mcommerceadminapp.model.remote_source.products.IProductRemoteSource
import com.example.mcommerceadminapp.pojo.products.Products
import okhttp3.RequestBody

class ProductFakeDataSource private constructor() :IProductRemoteSource {


    companion object {
        private var remoteSource: ProductFakeDataSource? = null
        fun getInstance(): ProductFakeDataSource {
            return remoteSource ?: ProductFakeDataSource()
        }
    }

    var listProuct: MutableList<Products> = mutableListOf()

    fun setProduct(products :MutableList<Products>){
        listProuct = products
    }
    override suspend fun getAllProducts(): ArrayList<Products> {
        listProuct.let {
            return ArrayList(it)
        }
        return ArrayList()
    }

    override suspend fun addProduct(requestBody: RequestBody): Products {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProductByID(productID: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setInventoryLevel(requestBody: RequestBody) {
        TODO("Not yet implemented")
    }
}