package com.example.mcommerceadminapp.model.shopify_repository.coupon

import com.example.mcommerceadminapp.pojo.coupon.PriceRules

interface ICouponRepo {

    suspend fun getAllPriceRules()
    suspend fun updatePriceRule(priceRuleID: String, priceRule: PriceRules)
     suspend fun createPriceRule(priceRule: PriceRules): PriceRules
    suspend fun deletePriceRuleID(priceRuleID:String)

    }