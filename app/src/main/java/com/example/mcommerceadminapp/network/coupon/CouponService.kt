package com.example.mcommerceadminapp.network.coupon

import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface CouponService {


    @Headers(
        "X-Shopify-Access-Token: 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",
        "Content-Type: application/json"
    )
    @GET("{resource}")
    suspend fun getAllPriceRules(
        @Path("resource", encoded = true) resources: String
    ): Response<JsonObject>
   ////////////////

    @Headers(
        "X-Shopify-Access-Token: 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",
        "Content-Type: application/json"
    )
    @POST("{resource}")
    suspend fun createPriceRule(
        @Path("resource", encoded = true) resources: String,
        @Body requestBody: RequestBody): Response<JsonObject>
    ///////////////

    @Headers(
        "X-Shopify-Access-Token: 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",
        "Content-Type: application/json"
    )
    @PUT("{resource}/{priceRuleId}.json")
    suspend fun updatePriceRule(
        @Path("resource", encoded = true) resources: String,
        @Path("priceRuleId", encoded = true) priceRule: String,
        @Body requestBody: RequestBody): Response<JsonObject>
    ///////////

    @Headers(
        "X-Shopify-Access-Token: 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",
        "Content-Type: application/json"
    )
    @DELETE("{resource}/{priceRuleId}.json")
    suspend fun deletePriceRuleId(
        @Path("resource", encoded = true) orderID: String,
        @Path("priceRuleId", encoded = true) priceRuleId: String,

        ): Response<JsonObject>
    //////

    @Headers(
        "X-Shopify-Access-Token: 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",
        "Content-Type: application/json"
    )
    @GET("{priceRule}/{priceRuleId}/{resource}")
    suspend fun getAllDiscountCode(
        @Path("priceRule", encoded = true) orderID: String,
        @Path("priceRuleId", encoded = true) priceRuleId: String,
        @Path("resource", encoded = true) resource: String

        ): Response<JsonObject>
    ////////

    @Headers(
    "X-Shopify-Access-Token: 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",
    "Content-Type: application/json"
  )
  @DELETE("{priceRule}/{priceRuleId}/{discountCode}/{discountCodeId}.json")
   suspend fun deleteDiscountCodeId(
    @Path("priceRule", encoded = true) priceRule: String,
    @Path("priceRuleId", encoded = true) priceRuleId: String,
    @Path("discountCode", encoded = true) discountCode: String,
    @Path("discountCodeId", encoded = true) discountCodeId: String,

    ): Response<JsonObject>


/////////////
    @Headers(
        "X-Shopify-Access-Token: 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",
        "Content-Type: application/json"
    )
    @POST("{priceRule}/{priceRuleId}/{discountCode}")
    suspend fun createDiscountCode(
        @Path("priceRule", encoded = true) priceRule: String,
        @Path("priceRuleId", encoded = true) priceRuleId: String,
        @Path("discountCode", encoded = true) discountCode: String,
        @Body requestBody: RequestBody): Response<JsonObject>

}
