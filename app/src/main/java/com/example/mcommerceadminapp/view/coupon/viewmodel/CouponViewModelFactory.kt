package com.example.mcommerceadminapp.view.coupon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mcommerceadminapp.model.remote_source.coupon.ICoupon
import com.example.mcommerceadminapp.model.shopify_repository.coupon.ICouponRepo

class CouponViewModelFactory (private val iCouponRepo: ICouponRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CouponViewModel::class.java)) {
            CouponViewModel(iCouponRepo) as T
        } else {
            throw IllegalArgumentException("Error")
        }
    }
}