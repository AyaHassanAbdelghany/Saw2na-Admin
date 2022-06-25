package com.example.mcommerceadminapp.model.shopify_repository.products

import android.util.Log
import com.example.mcommerceadminapp.model.Keys
import com.example.mcommerceadminapp.model.remote_source.products.IProductRemoteSource
import com.example.mcommerceadminapp.pojo.products.Products
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

class ProductsRepo private constructor(private val source: IProductRemoteSource) {

    companion object{
        private val productsRepo:ProductsRepo? = null

        fun getInstance(source: IProductRemoteSource):ProductsRepo{
            return productsRepo ?: ProductsRepo(source)
        }
    }

    suspend fun getAllProducts():ArrayList<Products>{
        return source.getAllProducts()
    }

    suspend fun addProduct(products: Products):Products{
        return source.addProduct(getProductRequestBody(products))
//        getProductRequestBody(products)
//        return Products()

    }

    suspend fun deleteProductByID(productID:String){
        source.deleteProductByID(productID)
    }

    suspend fun setInventoryLevel(inventoryID:Long,amount:Int){
        source.setInventoryLevel(getLevelRequestBody(inventoryID,amount))
    }

    suspend fun addProductImage(productID: String,imageCode: String){
        source.addProductImage(productID,getImageRequestBody(imageCode))
    }

    private fun getProductRequestBody(products: Products): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("title", products.title)
        jsonReq.put("vendor", products.vendor)
        jsonReq.put("product_type", products.productType)
        jsonReq.put("body_html", products.bodyHtml)

        val variantJson = JSONObject()
        variantJson.put("option1",products.variants[0].option1)
        variantJson.put("option2",products.variants[0].option2)
        variantJson.put("price",products.variants[0].price)
        variantJson.put("inventory_management","shopify")

        val variantsArray = JSONArray()
        variantsArray.put(variantJson)

        jsonReq.put("variants",variantsArray)


        val req = JSONObject()
        req.put("product", jsonReq)
        Log.e("tag","getProductRequestBody:  $req")
        return req.toString().toRequestBody("application/json".toMediaTypeOrNull())
    }

    private fun getLevelRequestBody(inventoryID:Long,amount:Int): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("location_id", Keys.INVENTORY_LOCATION)
        jsonReq.put("inventory_item_id", inventoryID)
        jsonReq.put("available", amount)

        return jsonReq.toString().toRequestBody("application/json".toMediaTypeOrNull())
    }

    private fun getImageRequestBody(imageCode:String): RequestBody {

        val imageJson = JSONObject()
        imageJson.put("attachment",imageCode +"\n")
        imageJson.put("filename","imageCode.png")

        val reqJson = JSONObject()
        reqJson.put("image",imageJson)

        return reqJson.toString().toRequestBody("application/json".toMediaTypeOrNull())
    }

}
