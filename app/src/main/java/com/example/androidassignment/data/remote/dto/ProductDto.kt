package com.example.androidassignment.data.remote.dto

import com.example.androidassignment.domain.model.Product

data class ProductDto(
    val color: String,
    val id: String,
    val image_path: String,
    val information: String,
    val long_description: String,
    val merchant_id: String,
    val price: Int,
    val short_description: String,
    val specifications: String,
    val stock: Int,
    val title: String
)

fun ProductDto.asDomainModel(): Product {
    return Product(title = title)
}