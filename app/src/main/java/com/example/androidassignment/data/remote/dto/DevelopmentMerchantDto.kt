package com.example.androidassignment.data.remote.dto

data class DevelopmentMerchantDto(
    val company_name: String,
    val coupon_enabled: Int,
    val delivery_text: String,
    val id: String,
    val logo_path: String,
    val long_description: String,
    val order_message: String,
    val products: List<ProductDto>,
    val shipping_type: String,
    val short_description: String,
    val standard_shipping_cost: Int,
    val url_tag: String
)