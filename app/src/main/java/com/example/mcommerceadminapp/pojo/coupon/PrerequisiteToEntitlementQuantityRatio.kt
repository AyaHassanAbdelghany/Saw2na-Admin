package com.example.mcommerceadminapp.pojo.coupon

import com.google.gson.annotations.SerializedName

data class PrerequisiteToEntitlementQuantityRatio(

    @SerializedName("prerequisite_quantity" )
    var prerequisiteQuantity : String? ,
    @SerializedName("entitled_quantity")
    var entitledQuantity     : String?
)
