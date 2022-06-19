package com.example.mcommerceadminapp.network.coupon

import com.example.mcommerceadminapp.model.Keys
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface CouponService {

    @Headers(
        "X-Shopify-Access-Token: ${Keys.Shopify_Access_Token}",
        "Content-Type: ${Keys.Content_Type}"
    )
    @GET("{resource}")
    suspend fun getAllPriceRules(
        @Path("resource", encoded = true) resources: String
    ): Response<JsonObject>
   ////////////////

    @Headers(
        "X-Shopify-Access-Token: ${Keys.Shopify_Access_Token}",
        "Content-Type: ${Keys.Content_Type}"
    )
    @POST("{resource}")
    suspend fun createPriceRule(
        @Path("resource", encoded = true) resources: String,
        @Body requestBody: RequestBody): Response<JsonObject>
    ///////////////

    @Headers(
        "X-Shopify-Access-Token: ${Keys.Shopify_Access_Token}",
        "Content-Type: ${Keys.Content_Type}"
    )
    @PUT("{resource}/{priceRuleId}.json")
    suspend fun updatePriceRule(
        @Path("resource", encoded = true) resources: String,
        @Path("priceRuleId", encoded = true) priceRule: String,
        @Body requestBody: RequestBody): Response<JsonObject>
    ///////////

    @Headers(
        "X-Shopify-Access-Token: ${Keys.Shopify_Access_Token}",
        "Content-Type: ${Keys.Content_Type}"
    )
    @DELETE("{resource}/{priceRuleId}.json")
    suspend fun deletePriceRule(
        @Path("resource", encoded = true) orderID: String,
        @Path("priceRuleId", encoded = true) priceRuleId: String,

        ): Response<JsonObject>
    //////

    @Headers(
        "X-Shopify-Access-Token: ${Keys.Shopify_Access_Token}",
        "Content-Type: ${Keys.Content_Type}"
    )
    @GET("{priceRule}/{priceRuleId}/{resource}")
    suspend fun getAllDiscountCode(
        @Path("priceRule", encoded = true) orderID: String,
        @Path("priceRuleId", encoded = true) priceRuleId: String,
        @Path("resource", encoded = true) resource: String

        ): Response<JsonObject>
    ////////

    @Headers(
        "X-Shopify-Access-Token: ${Keys.Shopify_Access_Token}",
        "Content-Type: ${Keys.Content_Type}"
    )
  @DELETE("{priceRule}/{priceRuleId}/{discountCode}/{discountCodeId}.json")
   suspend fun deleteDiscountCode(
    @Path("priceRule", encoded = true) priceRule: String,
    @Path("priceRuleId", encoded = true) priceRuleId: String,
    @Path("discountCode", encoded = true) discountCode: String,
    @Path("discountCodeId", encoded = true) discountCodeId: String,

    ): Response<JsonObject>


/////////////
@Headers(
    "X-Shopify-Access-Token: ${Keys.Shopify_Access_Token}",
    "Content-Type: ${Keys.Content_Type}"
)
    @POST("{priceRule}/{priceRuleId}/{discountCode}")
    suspend fun createDiscountCode(
        @Path("priceRule", encoded = true) priceRule: String,
        @Path("priceRuleId", encoded = true) priceRuleId: String,
        @Path("discountCode", encoded = true) discountCode: String,
        @Body requestBody: RequestBody): Response<JsonObject>

    //////////////
    @Headers(
        "X-Shopify-Access-Token: ${Keys.Shopify_Access_Token}",
        "Content-Type: ${Keys.Content_Type}"
    )
    @PUT("{priceRule}/{priceRuleId}/{discountCode}/{discountCodeId}.json")
    suspend fun updateDiscountCode(
        @Path("priceRule", encoded = true) priceRule: String,
        @Path("priceRuleId", encoded = true) priceRuleId: String,
        @Path("discountCode", encoded = true) discountCode: String,
        @Path("discountCodeId", encoded = true) discountCodeId: String,
        @Body requestBody: RequestBody): Response<JsonObject>

}
