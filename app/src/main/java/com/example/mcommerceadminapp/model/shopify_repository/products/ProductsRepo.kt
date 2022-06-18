package com.example.mcommerceadminapp.model.shopify_repository.products

import com.example.mcommerceadminapp.model.Keys
import com.example.mcommerceadminapp.model.remote_source.products.ProductsRemoteSource
import com.example.mcommerceadminapp.pojo.products.Products
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class ProductsRepo private constructor(private val source: ProductsRemoteSource) {

    companion object{
        private val productsRepo:ProductsRepo? = null

        fun getInstance(source: ProductsRemoteSource):ProductsRepo{
            return productsRepo ?: ProductsRepo(source)
        }
    }

    suspend fun getAllProducts():ArrayList<Products>{
        return source.getAllProducts()
    }

    suspend fun addProduct(products: Products):Products{
        return source.addProduct(getProductRequestBody(products))
    }

    suspend fun deleteProductByID(productID:String){
        source.deleteProductByID(productID)
    }

    suspend fun setInventoryLevel(inventoryID:Long,amount:Int){
        source.setInventoryLevel(getLevelRequestBody(inventoryID,amount))
    }

    private fun getProductRequestBody(products: Products): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("title", products.title)
        jsonReq.put("vendor", products.vendor)
        jsonReq.put("product_type", products.productType)

        val req = JSONObject()
        req.put("product", jsonReq)
        return req.toString().toRequestBody("application/json".toMediaTypeOrNull())
    }

    private fun getLevelRequestBody(inventoryID:Long,amount:Int): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("location_id", Keys.INVENTORY_LOCATION)
        jsonReq.put("inventory_item_id", inventoryID)
        jsonReq.put("available", amount)

        return jsonReq.toString().toRequestBody("application/json".toMediaTypeOrNull())
    }

}