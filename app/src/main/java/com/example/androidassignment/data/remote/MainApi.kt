package com.example.androidassignment.data.remote

import com.example.androidassignment.data.remote.dto.ProductListResponseDto
import retrofit2.http.GET

interface MainApi {

    @GET("/api/rest/products/venz")
    suspend fun getProductList(): ProductListResponseDto

}