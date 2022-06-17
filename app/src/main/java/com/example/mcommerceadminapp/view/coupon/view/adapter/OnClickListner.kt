package com.example.mcommerceadminapp.view.coupon.view.adapter

import com.example.mcommerceadminapp.pojo.coupon.price_rule.PriceRules

interface OnClickListner {

    fun onClick(id: String?,type:String)
    fun onClickEdit(priceRule :PriceRules,type:String)
}