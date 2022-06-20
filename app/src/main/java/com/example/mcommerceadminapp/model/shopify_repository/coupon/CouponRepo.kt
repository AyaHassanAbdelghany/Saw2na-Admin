package com.example.mcommerceadminapp.model.shopify_repository.coupon


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mcommerceadminapp.model.remote_source.coupon.RemoteSource
import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import com.example.mcommerceadminapp.model.shopify_repository.Result


class CouponRepo private  constructor(private var iCouponRemoteSource : RemoteSource):ICouponRepo {

    companion object {
       @Volatile private var couponRepo: CouponRepo? = null
        val allPriceRules = MutableLiveData<ArrayList<PriceRules>>()
        val allDiscountCode = MutableLiveData<ArrayList<DiscountCodes>>()

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(iCoupon: RemoteSource): CouponRepo {
            return couponRepo ?:synchronized(this){
                CouponRepo(iCoupon)
            }
        }
    }

    override suspend fun getAllPriceRules()   {
            var res = iCouponRemoteSource.getAllPriceRules()
            res.reverse()
            allPriceRules.postValue(res)
    }

    override suspend fun createPriceRule(priceRule: PriceRules) {
      val res = iCouponRemoteSource.createPriceRule(getRequestPriceRule(priceRule))
        val list = allPriceRules.value
        list!!.add(res)
        allPriceRules.postValue(list)
    }

    override suspend fun deletePriceRule(priceRuleID: String) {
        iCouponRemoteSource.deletePriceRuleID(priceRuleID)
    }

    override suspend fun updatePriceRule(priceRuleID: String, priceRule: PriceRules) {
        iCouponRemoteSource.updatePriceRule(priceRuleID, getRequestPriceRule(priceRule))
        getAllPriceRules()
    }


    ////////////
    override suspend fun getAllDiscountCode(priceRuleID:String) {
        allDiscountCode.postValue(ArrayList())
        allDiscountCode.postValue(iCouponRemoteSource.getAllDiscountCode(priceRuleID))
    }

    override suspend fun createDiscountCode(priceRuleId :String,discountCode: DiscountCodes) {
        val res = iCouponRemoteSource.createDiscountCode(priceRuleId,getRequestDiscountCode(discountCode))
        val list = allDiscountCode.value
        list!!.add(res)
        allDiscountCode.postValue(list)
    }

    override suspend fun deleteDiscountCode(priceRuleID:String, discountCodeID: String){
    iCouponRemoteSource.deleteDiscountCodeID(priceRuleID,discountCodeID)
        getAllDiscountCode(priceRuleID)
    }

    override suspend fun updateDiscountCode(
        priceRuleID: String,
        discountCodeID: String,
        discountCode: DiscountCodes
    ) {
        iCouponRemoteSource.updateDiscountCode(priceRuleID, discountCodeID,getRequestDiscountCode(discountCode))
    }


    private fun getRequestPriceRule(priceRule: PriceRules): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("title", priceRule.title)
        jsonReq.put("starts_at", priceRule.startsAt)
        jsonReq.put("ends_at", priceRule.endsAt)
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