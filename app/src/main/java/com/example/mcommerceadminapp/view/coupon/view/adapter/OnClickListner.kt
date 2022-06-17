package com.example.mcommerceadminapp.view.coupon.view.adapter

import com.example.mcommerceadminapp.pojo.coupon.discount_code.DiscountCodes
import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules

interface OnClickListner {

    fun onClick(id: String?,type:String)
    fun <T> onClickEdit(typeObject:T, type:String)

}