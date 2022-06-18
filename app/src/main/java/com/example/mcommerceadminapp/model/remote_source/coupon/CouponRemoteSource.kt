package com.example.mcommerceadminapp.model.remote_source.coupon

import com.example.mcommerceadminapp.model.Keys
import com.example.mcommerceadminapp.network.ShopifyRetrofitHelper
import com.example.mcommerceadminapp.network.coupon.CouponService
import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody

class CouponRemoteSource :ICoupon {

    private val api: CouponService =
        ShopifyRetrofitHelper.getInstance().create(CouponService::class.java)
    private val gson = Gson()

    override suspend fun createPriceRule(req: RequestBody): PriceRules {
        val res = api.createPriceRule(Keys.PRICE_RULES_JSON,req)
        return gson.fromJson(
            res.body()!!.get("price_rule") as JsonObject,
            object : TypeToken<PriceRules>() {}.type
        )
    }
    override suspend fun getAllPriceRules(): ArrayList<PriceRules> {
        val res = api.getAllPriceRules(Keys.PRICE_RULES_JSON)
        return gson.fromJson(
            res.body()!!.get("price_rules") as JsonArray,
            object : TypeToken<ArrayList<PriceRules>>() {}.type
        )
    }
    override suspend fun updatePriceRule(priceRuleID: String, req: RequestBody) {
        val res = api.updatePriceRule(Keys.PRICE_RULES,priceRuleID,req)
        return gson.fromJson(
            res.body()!!.get("price_rule") as JsonObject,
            object : TypeToken<PriceRules>() {}.type
        )
    }
    override suspend fun deletePriceRuleID(priceRuleID:String) {
         api.deletePriceRule(Keys.PRICE_RULES,priceRuleID)
    }

    override suspend fun getAllDiscountCode(priceRuleID:String) : ArrayList<DiscountCodes> {
        val res = api.getAllDiscountCode(Keys.PRICE_RULES,priceRuleID,Keys.DISCOUNT_CODE_JSON)
        return gson.fromJson(
            res.body()!!.get("discount_codes") as JsonArray,
            object : TypeToken<ArrayList<DiscountCodes>>() {}.type
        )
    }

   override suspend fun deleteDiscountCodeID(priceRuleID:String ,discountCodeID: String)
    {
        api.deleteDiscountCode(Keys.PRICE_RULES,priceRuleID,Keys.DISCOUNT_CODE,discountCodeID)

    }

    override suspend fun createDiscountCode(priceRuleId :String,req: RequestBody): DiscountCodes {
        val res = api.createDiscountCode(Keys.PRICE_RULES,priceRuleId,Keys.DISCOUNT_CODE_JSON,req)
        return gson.fromJson(
            res.body()!!.get("discount_code") as JsonObject,
            object : TypeToken<DiscountCodes>() {}.type
        )
    }

    override suspend fun updateDiscountCode(
        priceRuleID: String,
        discountCodeID: String,
        req: RequestBody
    ) {
        val res = api.updateDiscountCode(Keys.PRICE_RULES,priceRuleID,
            Keys.DISCOUNT_CODE,discountCodeID,req)
        return gson.fromJson(
            res.body()!!.get("discount_code") as JsonObject,
            object : TypeToken<DiscountCodes>() {}.type
        )    }
}