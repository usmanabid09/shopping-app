package com.example.androidassignment.domain.repository

import com.example.androidassignment.common.Resource
import com.example.androidassignment.data.remote.dto.ProductListResponseDto

interface ProductRepository {
    suspend fun getProductList(): Resource<ProductListResponseDto>
}