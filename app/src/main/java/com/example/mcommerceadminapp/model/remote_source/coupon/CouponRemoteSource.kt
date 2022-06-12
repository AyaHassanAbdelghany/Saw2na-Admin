package com.example.mcommerceadminapp.model.remote_source.coupon

import com.example.mcommerceadminapp.model.Keys
import com.example.mcommerceadminapp.network.ShopifyRetrofitHelper
import com.example.mcommerceadminapp.network.coupon.CouponService
import com.example.mcommerceadminapp.pojo.coupon.PriceRules
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody

class CouponRemoteSource :ICoupon {

    private val api: CouponService =
        ShopifyRetrofitHelper.getInstance().create(CouponService::class.java)
    private val gson = Gson()

    override suspend fun createPriceRule(req: RequestBody):PriceRules {
        val res = api.createPriceRule(Keys.PRICE_RULES_JSON,req)

        return gson.fromJson(
            res.body()!!.get("price_rules") as JsonArray,
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
            res.body()!!.get("price_rules") as JsonArray,
            object : TypeToken<PriceRules>() {}.type
        )
    }
    override suspend fun deletePriceRuleID(priceRuleID:String) {
         api.deletePriceRuleId(Keys.PRICE_RULES,priceRuleID)
    }
}