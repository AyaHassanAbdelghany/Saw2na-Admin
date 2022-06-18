package com.example.example

import com.google.gson.annotations.SerializedName


data class InventoryItem (

  @SerializedName("id"                              ) var id                           : Long?              = null,
  @SerializedName("sku"                             ) var sku                          : String?           = null,
  @SerializedName("created_at"                      ) var createdAt                    : String?           = null,
  @SerializedName("updated_at"                      ) var updatedAt                    : String?           = null,
  @SerializedName("requires_shipping"               ) var requiresShipping             : Boolean?          = null,
  @SerializedName("cost"                            ) var cost                         : String?           = null,
  @SerializedName("country_code_of_origin"          ) var countryCodeOfOrigin          : String?           = null,
  @SerializedName("province_code_of_origin"         ) var provinceCodeOfOrigin         : String?           = null,
  @SerializedName("harmonized_system_code"          ) var harmonizedSystemCode         : String?           = null,
  @SerializedName("tracked"                         ) var tracked                      : Boolean?          = null,
  @SerializedName("country_harmonized_system_codes" ) var countryHarmonizedSystemCodes : ArrayList<String> = arrayListOf(),
  @SerializedName("admin_graphql_api_id"            ) var adminGraphqlApiId            : String?           = null

)