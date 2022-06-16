package com.example.mcommerceadminapp.view.Coupon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcommerceadminapp.model.shopify_repository.coupon.CouponRepo
import com.example.mcommerceadminapp.model.shopify_repository.coupon.ICouponRepo
import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiscountCodeViewModel (private val iCouponRepo: ICouponRepo) :ViewModel(){

    private val _allDiscountCode : MutableLiveData<ArrayList<DiscountCodes>> = CouponRepo.allDiscountCode
    var allDiscountCode: LiveData<ArrayList<DiscountCodes>> = _allDiscountCode

     fun getAllDiscountCode(priceRuleID:String){
         viewModelScope.launch (Dispatchers.IO) {
             iCouponRepo.getAllDiscountCode(priceRuleID)
         }
     }

    fun deleteDiscountCodeID(priceRuleId:String ,discountCodeId: String) {
        viewModelScope.launch (Dispatchers.IO) {
            iCouponRepo.deleteDiscountCodeID(priceRuleId,discountCodeId)
        }
    }
    fun createDiscountCode(priceRuleId :String ,discountCode: DiscountCodes) {
        viewModelScope.launch (Dispatchers.IO) {
            iCouponRepo.createDiscountCode(priceRuleId,discountCode)
        }
    }
    }