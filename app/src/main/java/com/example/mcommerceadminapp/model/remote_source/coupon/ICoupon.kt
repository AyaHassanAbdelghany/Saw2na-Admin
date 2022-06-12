package com.example.mcommerceadminapp.model.remote_source.coupon

import com.example.mcommerceadminapp.pojo.coupon.PriceRules
import okhttp3.RequestBody

interface ICoupon {

    suspend fun createPriceRule(req: RequestBody):PriceRules
    suspend fun getAllPriceRules() : ArrayList<PriceRules>
    suspend fun updatePriceRule(priceRuleID: String, req :RequestBody)
    suspend fun deletePriceRuleID(priceRuleID:String)

    }
