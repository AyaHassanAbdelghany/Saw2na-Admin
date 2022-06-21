package com.example.mcommerceadminapp.coupon.data_source

import com.example.mcommerceadminapp.model.remote_source.coupon.ICouponRemoteSource
import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import okhttp3.RequestBody



class CouponFakeDataSource  private constructor(): ICouponRemoteSource{


    companion object {
        private var remoteSource: CouponFakeDataSource? = null
        fun getInstance(): CouponFakeDataSource {
            return remoteSource ?: CouponFakeDataSource()
        }
    }

    var listPriceRule : MutableList<PriceRules> = mutableListOf()
    var listDiscountCode: MutableList<DiscountCodes> = mutableListOf()

    fun setPriceRule(priceRules :MutableList<PriceRules>){
        listPriceRule = priceRules
    }

    fun setDiscountCode(discountCode :MutableList<DiscountCodes>){
        listDiscountCode = discountCode
    }

    override suspend fun getAllDiscountCode(priceRuleID: String): ArrayList<DiscountCodes> {
        listDiscountCode.let {
            return ArrayList(it)
        }
        return ArrayList()
    }

    override suspend fun getAllPriceRules(): ArrayList<PriceRules> {
        listPriceRule.let {
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

    override suspend fun deleteDiscountCodeID(priceRuleID: String, discountCodeID: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createPriceRule(req: RequestBody): PriceRules {
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