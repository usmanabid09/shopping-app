package com.example.androidassignment.domain.use_case

import com.example.androidassignment.common.Resource
import com.example.androidassignment.common.State
import com.example.androidassignment.data.remote.dto.asDomainModel
import com.example.androidassignment.domain.model.Product
import com.example.androidassignment.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<State<List<Product>>> = flow {
        try {
            emit(State.Loading())
            when (val resource = repository.getProductList()) {
                is Resource.Success -> {
                    val response = resource.data!!
                    val products: ArrayList<Product> = arrayListOf()
                    response.development_merchants.forEach { productList ->
                        products.addAll(
                            productList.products.map { product -> product.asDomainModel() }
                        )
                    }
                    emit(State.Success(products))
                }
                is Resource.Error -> {
                    emit(State.Error(resource.message ?: "Something went wrong while loading product list"))
                }
            }
        } catch (ex: Exception) {
            emit(State.Error(ex.localizedMessage ?: "Something went wrong while loading product list"))
        }

    }
}