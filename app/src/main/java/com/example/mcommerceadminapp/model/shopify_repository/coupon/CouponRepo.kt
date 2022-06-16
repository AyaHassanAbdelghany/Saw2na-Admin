package com.example.mcommerceadminapp.model.shopify_repository.coupon


import androidx.lifecycle.MutableLiveData
import com.example.mcommerceadminapp.model.remote_source.coupon.ICoupon
import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class CouponRepo private  constructor(private var iCouponRemoteSource : ICoupon):ICouponRepo {

    companion object {
        private val couponRepo: CouponRepo? = null
        val allPriceRules = MutableLiveData<ArrayList<PriceRules>>()
        val allDiscountCode = MutableLiveData<ArrayList<DiscountCodes>>()

        fun getInstance(iCoupon: ICoupon): CouponRepo {
            return couponRepo ?: CouponRepo(iCoupon)
        }
    }

    override suspend fun getAllPriceRules() {
        val res = iCouponRemoteSource.getAllPriceRules()
        res.reverse()
        allPriceRules.postValue(res)
    }

    override suspend fun createPriceRule(priceRule: PriceRules) {
      val res = iCouponRemoteSource.createPriceRule(getRequestPriceRule(priceRule))
        val list = allPriceRules.value
        list!!.add(res)
        allPriceRules.postValue(list)
    }

    override suspend fun deletePriceRuleID(priceRuleID: String) {
        iCouponRemoteSource.deletePriceRuleID(priceRuleID)
    }

    override suspend fun updatePriceRule(priceRuleID: String, priceRule: PriceRules) {
        iCouponRemoteSource.updatePriceRule(priceRuleID, getRequestPriceRule(priceRule))
        getAllPriceRules()

    }


    ////////////
    override suspend fun getAllDiscountCode(priceRuleID:String) {
        allDiscountCode.postValue(iCouponRemoteSource.getAllDiscountCode(priceRuleID))
    }

    override suspend fun createDiscountCode(priceRuleId :String,discountCode: DiscountCodes) {
        val res = iCouponRemoteSource.createDiscountCode(priceRuleId,getRequestDiscountCode(discountCode))
        val list = allDiscountCode.value
        list!!.add(res)
        allDiscountCode.postValue(list)
    }

    override suspend fun deleteDiscountCodeID(priceRuleID:String ,discountCodeID: String){
    iCouponRemoteSource.deleteDiscountCodeID(priceRuleID,discountCodeID)
        getAllDiscountCode(priceRuleID)
    }
    private fun getRequestPriceRule(priceRule: PriceRules): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("title", priceRule.title)
        jsonReq.put("starts_at", priceRule.startsAt)
        jsonReq.put("usage_limit", priceRule.usageLimit)
        jsonReq.put("value", priceRule.value)
        jsonReq.put("allocation_method", "across")
        jsonReq.put("customer_selection", "all")
        jsonReq.put("value_type", "fixed_amount")
        jsonReq.put("target_selection", "all")
        jsonReq.put("target_type","line_item")

        val req = JSONObject()
        req.put("price_rule", jsonReq)
        val requestBody = req.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return requestBody
    }

    private fun getRequestDiscountCode(discountCode: DiscountCodes): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("code", discountCode.code)

        val req = JSONObject()
        req.put("discount_code", jsonReq)
        val requestBody = req.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return requestBody
    }
}