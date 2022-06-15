package com.example.mcommerceadminapp.model

import com.example.mcommerceadminapp.pojo.products.Review


class Keys {
    companion object {

        const val PRICE_RULES ="price_rules"
        const val PRICE_RULES_JSON ="price_rules.json"
        const val PRODUCTS ="products.json"


        const val Shopify_Access_Token = "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"
        const val Content_Type = "application/json"

        const val BASE_URL: String ="https://madalex20220.myshopify.com/admin/api/2022-01/"

        val REVIEWS = listOf(
            Review(name = "Mariam", rate = 5.0F, date = "05/04/2021", desc = "This is so cool and very comfortable."),
            Review(name = "Mahmoud", rate = 3.5F, date = "25/10/2021", desc = "This is so cool and very comfortable."),
            Review(name = "Alaa", rate = 4.0F, date = "01/01/2021", desc = "This is so cool and very comfortable."),
            Review(name = "Ali", rate = 4.5F, date = "16/06/2021", desc = "This is so cool and very comfortable."),

            )

    }
}