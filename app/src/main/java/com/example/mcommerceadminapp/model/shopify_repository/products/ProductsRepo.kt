package com.example.mcommerceadminapp.model.shopify_repository.products

import android.util.Log
import com.example.mcommerceadminapp.model.Keys
import com.example.mcommerceadminapp.model.remote_source.products.IProductRemoteSource
import com.example.mcommerceadminapp.model.remote_source.products.ProductsRemoteSource
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
        getProductRequestBody(products)
        return Products()
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
        jsonReq.put("body_html", products.bodyHtml)

        val variantJson = JSONObject()
        variantJson.put("option1",products.variants[0].option1)
        variantJson.put("option2",products.variants[0].option2)
        variantJson.put("price",products.variants[0].price)
        val variantsArray = JSONArray()
        variantsArray.put(variantJson)

        val imageJson = JSONObject()
        imageJson.put("src",products.image!!.src)

        //jsonReq.put("image",imageJson)
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

}
//
//{
//    "product": {
//    "id": 632910392,
//    "title": "IPod Nano - 8GB",
//    "body_html": "<p>It's the small iPod with one very big idea: Video. Now the world's most popular music player, available in 4GB and 8GB models, lets you enjoy TV shows, movies, video podcasts, and more. The larger, brighter display means amazing picture quality. In six eye-catching colors, iPod nano is stunning all around. And with models starting at just $149, little speaks volumes.</p>",
//    "vendor": "Apple",
//    "product_type": "Cult Products",
//    "created_at": "2022-03-11T11:29:03-05:00",
//    "handle": "ipod-nano",
//    "updated_at": "2022-03-11T11:29:03-05:00",
//    "published_at": "2007-12-31T19:00:00-05:00",
//    "template_suffix": null,
//    "status": "active",
//    "published_scope": "web",
//    "tags": "Emotive, Flash Memory, MP3, Music",
//    "admin_graphql_api_id": "gid://shopify/Product/632910392",
//    "variants": [
//    {
//        "id": 808950810,
//        "product_id": 632910392,
//        "title": "Pink",
//        "price": "199.00",
//        "sku": "IPOD2008PINK",
//        "position": 1,
//        "inventory_policy": "continue",
//        "compare_at_price": null,
//        "fulfillment_service": "manual",
//        "inventory_management": "shopify",
//        "option1": "Pink",
//        "option2": null,
//        "option3": null,
//        "created_at": "2022-03-11T11:29:03-05:00",
//        "updated_at": "2022-03-11T11:29:03-05:00",
//        "taxable": true,
//        "barcode": "1234_pink",
//        "grams": 567,
//        "image_id": 562641783,
//        "weight": 1.25,
//        "weight_unit": "lb",
//        "inventory_item_id": 808950810,
//        "inventory_quantity": 10,
//        "old_inventory_quantity": 10,
//        "presentment_prices": [
//        {
//            "price": {
//            "amount": "199.00",
//            "currency_code": "USD"
//        },
//            "compare_at_price": null
//        }
//        ],
//        "requires_shipping": true,
//        "admin_graphql_api_id": "gid://shopify/ProductVariant/808950810"
//    },
//    {
//        "id": 49148385,
//        "product_id": 632910392,
//        "title": "Red",
//        "price": "199.00",
//        "sku": "IPOD2008RED",
//        "position": 2,
//        "inventory_policy": "continue",
//        "compare_at_price": null,
//        "fulfillment_service": "manual",
//        "inventory_management": "shopify",
//        "option1": "Red",
//        "option2": null,
//        "option3": null,
//        "created_at": "2022-03-11T11:29:03-05:00",
//        "updated_at": "2022-03-11T11:29:03-05:00",
//        "taxable": true,
//        "barcode": "1234_red",
//        "grams": 567,
//        "image_id": null,
//        "weight": 1.25,
//        "weight_unit": "lb",
//        "inventory_item_id": 49148385,
//        "inventory_quantity": 20,
//        "old_inventory_quantity": 20,
//        "presentment_prices": [
//        {
//            "price": {
//            "amount": "199.00",
//            "currency_code": "USD"
//        },
//            "compare_at_price": null
//        }
//        ],
//        "requires_shipping": true,
//        "admin_graphql_api_id": "gid://shopify/ProductVariant/49148385"
//    },
//    {
//        "id": 39072856,
//        "product_id": 632910392,
//        "title": "Green",
//        "price": "199.00",
//        "sku": "IPOD2008GREEN",
//        "position": 3,
//        "inventory_policy": "continue",
//        "compare_at_price": null,
//        "fulfillment_service": "manual",
//        "inventory_management": "shopify",
//        "option1": "Green",
//        "option2": null,
//        "option3": null,
//        "created_at": "2022-03-11T11:29:03-05:00",
//        "updated_at": "2022-03-11T11:29:03-05:00",
//        "taxable": true,
//        "barcode": "1234_green",
//        "grams": 567,
//        "image_id": null,
//        "weight": 1.25,
//        "weight_unit": "lb",
//        "inventory_item_id": 39072856,
//        "inventory_quantity": 30,
//        "old_inventory_quantity": 30,
//        "presentment_prices": [
//        {
//            "price": {
//            "amount": "199.00",
//            "currency_code": "USD"
//        },
//            "compare_at_price": null
//        }
//        ],
//        "requires_shipping": true,
//        "admin_graphql_api_id": "gid://shopify/ProductVariant/39072856"
//    },
//    {
//        "id": 457924702,
//        "product_id": 632910392,
//        "title": "Black",
//        "price": "199.00",
//        "sku": "IPOD2008BLACK",
//        "position": 4,
//        "inventory_policy": "continue",
//        "compare_at_price": null,
//        "fulfillment_service": "manual",
//        "inventory_management": "shopify",
//        "option1": "Black",
//        "option2": null,
//        "option3": null,
//        "created_at": "2022-03-11T11:29:03-05:00",
//        "updated_at": "2022-03-11T11:29:03-05:00",
//        "taxable": true,
//        "barcode": "1234_black",
//        "grams": 567,
//        "image_id": null,
//        "weight": 1.25,
//        "weight_unit": "lb",
//        "inventory_item_id": 457924702,
//        "inventory_quantity": 40,
//        "old_inventory_quantity": 40,
//        "presentment_prices": [
//        {
//            "price": {
//            "amount": "199.00",
//            "currency_code": "USD"
//        },
//            "compare_at_price": null
//        }
//        ],
//        "requires_shipping": true,
//        "admin_graphql_api_id": "gid://shopify/ProductVariant/457924702"
//    }
//    ],
//    "options": [
//    {
//        "id": 594680422,
//        "product_id": 632910392,
//        "name": "Color",
//        "position": 1,
//        "values": [
//        "Pink",
//        "Red",
//        "Green",
//        "Black"
//        ]
//    }
//    ],
//    "images": [
//    {
//        "id": 850703190,
//        "product_id": 632910392,
//        "position": 1,
//        "created_at": "2022-03-11T11:29:03-05:00",
//        "updated_at": "2022-03-11T11:29:03-05:00",
//        "alt": null,
//        "width": 123,
//        "height": 456,
//        "src": "https://cdn.shopify.com/s/files/1/0005/4838/0009/products/ipod-nano.png?v=1647016143",
//        "variant_ids": [],
//        "admin_graphql_api_id": "gid://shopify/ProductImage/850703190"
//    },
//    {
//        "id": 562641783,
//        "product_id": 632910392,
//        "position": 2,
//        "created_at": "2022-03-11T11:29:03-05:00",
//        "updated_at": "2022-03-11T11:29:03-05:00",
//        "alt": null,
//        "width": 123,
//        "height": 456,
//        "src": "https://cdn.shopify.com/s/files/1/0005/4838/0009/products/ipod-nano-2.png?v=1647016143",
//        "variant_ids": [
//        808950810
//        ],
//        "admin_graphql_api_id": "gid://shopify/ProductImage/562641783"
//    },
//    {
//        "id": 378407906,
//        "product_id": 632910392,
//        "position": 3,
//        "created_at": "2022-03-11T11:29:03-05:00",
//        "updated_at": "2022-03-11T11:29:03-05:00",
//        "alt": null,
//        "width": 123,
//        "height": 456,
//        "src": "https://cdn.shopify.com/s/files/1/0005/4838/0009/products/ipod-nano.png?v=1647016143",
//        "variant_ids": [],
//        "admin_graphql_api_id": "gid://shopify/ProductImage/378407906"
//    }
//    ],
//    "image": {
//    "id": 850703190,
//    "product_id": 632910392,
//    "position": 1,
//    "created_at": "2022-03-11T11:29:03-05:00",
//    "updated_at": "2022-03-11T11:29:03-05:00",
//    "alt": null,
//    "width": 123,
//    "height": 456,
//    "src": "https://cdn.shopify.com/s/files/1/0005/4838/0009/products/ipod-nano.png?v=1647016143",
//    "variant_ids": [],
//    "admin_graphql_api_id": "gid://shopify/ProductImage/850703190"
//}
//}
//}