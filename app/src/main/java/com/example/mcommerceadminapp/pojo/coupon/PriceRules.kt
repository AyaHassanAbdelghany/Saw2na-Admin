package com.example.mcommerceadminapp.pojo.coupon

import com.google.gson.annotations.SerializedName

data class PriceRules(

    @SerializedName("id")
var id: Int?,
    @SerializedName("value_type")
var valueType: String?,
    @SerializedName("value")
var value: String?,
    @SerializedName("customer_selection")
var customerSelection: String?,
    @SerializedName("target_type")
var targetType: String?,
    @SerializedName("target_selection")
var targetSelection: String?,
    @SerializedName("allocation_method")
var allocationMethod: String?,
    @SerializedName("allocation_limit")
var allocationLimit: String?,
    @SerializedName("once_per_customer")
var oncePerCustomer: Boolean?,
    @SerializedName("usage_limit")
var usageLimit: String?,
    @SerializedName("starts_at")
var startsAt: String?,
    @SerializedName("ends_at")
var endsAt: String?,
    @SerializedName("created_at")
var createdAt: String?,
    @SerializedName("updated_at")
var updatedAt: String?,
    @SerializedName("entitled_product_ids")
var entitledProductIds: ArrayList<String>,
    @SerializedName("entitled_variant_ids")
var entitledVariantIds: ArrayList<String>,
    @SerializedName("entitled_collection_ids")
var entitledCollectionIds: ArrayList<String>,
    @SerializedName("entitled_country_ids")
var entitledCountryIds: ArrayList<String>,
    @SerializedName("prerequisite_product_ids")
var prerequisiteProductIds: ArrayList<String>,
    @SerializedName("prerequisite_variant_ids")
var prerequisiteVariantIds: ArrayList<String>,
    @SerializedName("prerequisite_collection_ids")
var prerequisiteCollectionIds: ArrayList<String>,
    @SerializedName("prerequisite_saved_search_ids")
var prerequisiteSavedSearchIds: ArrayList<String>,
    @SerializedName("prerequisite_customer_ids")
var prerequisiteCustomerIds: ArrayList<String>,
    @SerializedName("prerequisite_subtotal_range")
var prerequisiteSubtotalRange: String?,
    @SerializedName("prerequisite_quantity_range")
var prerequisiteQuantityRange: String?,
    @SerializedName("prerequisite_shipping_price_range")
var prerequisiteShippingPriceRange: String?,
    @SerializedName("prerequisite_to_entitlement_quantity_ratio")
var prerequisiteToEntitlementQuantityRatio: PrerequisiteToEntitlementQuantityRatio?,
    @SerializedName("prerequisite_to_entitlement_purchase")
var prerequisiteToEntitlementPurchase: PrerequisiteToEntitlementPurchase?,
    @SerializedName("title")
var title: String?,
    @SerializedName("admin_graphql_api_id")
var adminGraphqlApiId: String?

)