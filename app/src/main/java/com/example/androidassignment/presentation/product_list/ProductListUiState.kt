package com.example.androidassignment.presentation.product_list

import android.view.View
import com.example.androidassignment.domain.model.Product

sealed class ProductListUiState(
    val progressVisibility: Int,
    val productListVisibility: Int,
    val productList: List<Product>,
    val errorVisibility: Int,
    val error: String?
) {
    class LOADING : ProductListUiState(View.VISIBLE, View.GONE, emptyList(), View.GONE, null)
    data class SUCCESS(val products: List<Product>) : ProductListUiState(View.GONE, View.VISIBLE, products, View.GONE, null)
    data class ERROR(val message: String) : ProductListUiState(View.GONE, View.GONE, emptyList(), View.VISIBLE, message)
}