package com.example.androidassignment.data.repository

import com.example.androidassignment.common.Resource
import com.example.androidassignment.data.remote.MainApi
import com.example.androidassignment.data.remote.dto.ProductListResponseDto
import com.example.androidassignment.domain.repository.ProductRepository
import retrofit2.HttpException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: MainApi
) : ProductRepository {
    override suspend fun getProductList(): Resource<ProductListResponseDto> {
        return try {
            Resource.Success(api.getProductList())
        } catch (e: HttpException) { // If any other status other then 200 comes in response.
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: java.io.IOException) { // If internet or server isn't available
            Resource.Error("Couldn't reach server. Check your internet connection.")
        } catch (ex: Exception) {
            Resource.Error("Couldn't reach server. Check your internet connection.")
        }
    }
}