package com.example.mcommerceadminapp.pojo.coupon.price_rule

import com.google.gson.annotations.SerializedName

data class PriceRuleRespons (

    @SerializedName("price_rules" )
    var priceRules : ArrayList<PriceRules>

    )