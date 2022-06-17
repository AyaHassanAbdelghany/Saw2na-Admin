package com.example.mcommerceadminapp.model.shopify_repository.coupon

import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import okhttp3.RequestBody

interface ICouponRepo {

    suspend fun getAllPriceRules()
    suspend fun updatePriceRule(priceRuleID: String, priceRule: PriceRules)
     suspend fun createPriceRule(priceRule: PriceRules)
    suspend fun deletePriceRule(priceRuleID:String)

    suspend fun getAllDiscountCode(priceRuleID:String)
    suspend fun createDiscountCode(priceRuleId :String,discountCode: DiscountCodes)
    suspend fun deleteDiscountCode(priceRuleID:String, discountCodeID: String)
    suspend fun updateDiscountCode(priceRuleID: String,discountCodeID: String, discountCode: DiscountCodes)


}