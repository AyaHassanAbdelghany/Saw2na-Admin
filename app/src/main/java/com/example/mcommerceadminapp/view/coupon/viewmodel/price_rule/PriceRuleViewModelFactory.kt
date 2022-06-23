package com.example.mcommerceadminapp.view.coupon.viewmodel.price_rule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.model.shopify_repository.coupon.ICouponRepo

class PriceRuleViewModelFactory (private val iCouponRepo: ICouponRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PriceRuleViewModel::class.java)) {
            PriceRuleViewModel(iCouponRepo) as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }
}