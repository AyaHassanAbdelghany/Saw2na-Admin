package com.example.mcommerceadminapp.view.Coupon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcommerceadminapp.model.shopify_repository.coupon.CouponRepo
import com.example.mcommerceadminapp.model.shopify_repository.coupon.ICouponRepo
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CouponViewModel (private val iCouponRepo: ICouponRepo) :ViewModel(){

    private val _allPriceRules : MutableLiveData<ArrayList<PriceRules>> = CouponRepo.allPriceRules
    var allPriceRules: LiveData<ArrayList<PriceRules>> = _allPriceRules


    fun getAllPriceRules() {
        viewModelScope.launch (Dispatchers.IO) {
            iCouponRepo.getAllPriceRules()
        }
    }

    fun createPriceRule(priceRule: PriceRules) {
        viewModelScope.launch (Dispatchers.IO) {
            iCouponRepo.createPriceRule(priceRule)
        }
    }
   fun updatePriceRule(priceRuleID: String, priceRule: PriceRules) {
       viewModelScope.launch (Dispatchers.IO) {
           iCouponRepo.updatePriceRule(priceRuleID,priceRule)
       }
   }
  fun deletePriceRuleID(priceRuleID: String) {
      viewModelScope.launch (Dispatchers.IO) {
          iCouponRepo.deletePriceRuleID(priceRuleID)
      }
  }
}