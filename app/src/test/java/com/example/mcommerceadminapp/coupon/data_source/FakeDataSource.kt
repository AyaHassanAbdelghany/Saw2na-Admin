package com.example.mcommerceadminapp.coupon.data_source

import com.example.mcommerceadminapp.model.remote_source.coupon.RemoteSource
import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import okhttp3.RequestBody
import com.example.mcommerceadminapp.model.shopify_repository.Result
import com.example.mcommerceadminapp.network.ShopifyRetrofitHelper
import com.example.mcommerceadminapp.network.coupon.CouponService
import com.google.gson.Gson


class FakeDataSource (var list :MutableList<PriceRules>? = mutableListOf()): RemoteSource{


    companion object {
        private var remoteSource: FakeDataSource? = null
        fun getInstance(): FakeDataSource {
            return remoteSource ?: FakeDataSource()
        }
    }
    override suspend fun createPriceRule(req: RequestBody): PriceRules {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPriceRules(): ArrayList<PriceRules> {
        list.let {
            return ArrayList(it)
        }
        return ArrayList()
    }

    override suspend fun updatePriceRule(priceRuleID: String, req: RequestBody) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePriceRuleID(priceRuleID: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDiscountCode(priceRuleID: String): ArrayList<DiscountCodes> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDiscountCodeID(priceRuleID: String, discountCodeID: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createDiscountCode(priceRuleId: String, req: RequestBody): DiscountCodes {
        TODO("Not yet implemented")
    }

    override suspend fun updateDiscountCode(
        priceRuleID: String,
        discountCodeID: String,
        req: RequestBody
    ) {
        TODO("Not yet implemented")
    }
}