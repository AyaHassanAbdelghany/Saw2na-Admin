package com.example.mcommerceadminapp.model.remote_source.coupon

import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import okhttp3.RequestBody

interface ICoupon {

    suspend fun createPriceRule(req: RequestBody): PriceRules
    suspend fun getAllPriceRules() : ArrayList<PriceRules>
    suspend fun updatePriceRule(priceRuleID: String, req :RequestBody)
    suspend fun deletePriceRuleID(priceRuleID:String)


     suspend fun getAllDiscountCode(priceRuleID:String) : ArrayList<DiscountCodes>
    suspend fun deleteDiscountCodeID(priceRuleID:String ,discountCodeID: String)
    suspend fun createDiscountCode(priceRuleId :String ,req: RequestBody) :DiscountCodes


}
