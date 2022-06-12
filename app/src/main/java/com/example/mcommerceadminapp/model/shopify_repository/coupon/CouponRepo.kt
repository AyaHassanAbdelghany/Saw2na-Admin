package com.example.mcommerceadminapp.model.shopify_repository.coupon


import androidx.lifecycle.MutableLiveData
import com.example.mcommerceadminapp.model.remote_source.coupon.ICoupon
import com.example.mcommerceadminapp.pojo.coupon.PriceRules
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

class CouponRepo private  constructor(private var iCouponRemoteSource : ICoupon):ICouponRepo {

    companion object {
        private val couponRepo: CouponRepo? = null
        val allPriceRules = MutableLiveData<ArrayList<PriceRules>>()

        fun getInstance(iCoupon: ICoupon): CouponRepo {
            return couponRepo ?: CouponRepo(iCoupon)
        }
    }

    override suspend fun getAllPriceRules() {
        allPriceRules.postValue(iCouponRemoteSource.getAllPriceRules())
    }

    override suspend fun createPriceRule(priceRule: PriceRules): PriceRules {
        return iCouponRemoteSource.createPriceRule(getRequest(priceRule))
    }

    override suspend fun deletePriceRuleID(priceRuleID: String) {
        iCouponRemoteSource.deletePriceRuleID(priceRuleID)
        iCouponRemoteSource.getAllPriceRules()
    }

    override suspend fun updatePriceRule(priceRuleID: String, priceRule: PriceRules) {
        iCouponRemoteSource.updatePriceRule(priceRuleID, getRequest(priceRule))
        iCouponRemoteSource.getAllPriceRules()

    }

    private fun getRequest(priceRule: PriceRules): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("title", priceRule.title)
        jsonReq.put("starts_at", priceRule.startsAt)
        jsonReq.put("usage_limit", priceRule.usageLimit)
        jsonReq.put("value", priceRule.value)
        jsonReq.put("allocation_method", priceRule.allocationMethod)
        jsonReq.put("customer_selection", priceRule.customerSelection)
        jsonReq.put("target_selection", priceRule.targetSelection)

        val req = JSONObject()
        req.put("price_rules", jsonReq)
        val requestBody = req.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return requestBody
    }
}