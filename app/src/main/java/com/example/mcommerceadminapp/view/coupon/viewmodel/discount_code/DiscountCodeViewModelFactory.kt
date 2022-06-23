package com.example.mcommerceadminapp.view.coupon.viewmodel.discount_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.model.shopify_repository.coupon.ICouponRepo

class DiscountCodeViewModelFactory (private val iCouponRepo: ICouponRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DiscountCodeViewModel::class.java)) {
            DiscountCodeViewModel(iCouponRepo) as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }
}