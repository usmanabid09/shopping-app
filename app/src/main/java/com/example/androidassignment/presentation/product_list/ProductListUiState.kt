package com.example.androidassignment.presentation.product_list

import android.view.View
import com.example.androidassignment.domain.model.Product

sealed class ProductListUiState<T>(
    val progressVisibility: Int,
    val productListVisibility: Int,
    val productList: List<Product>,
    val errorVisibility: Int,
    val error: String?
) {
    class LOADING<T> : ProductListUiState<T>(View.VISIBLE, View.GONE, emptyList(), View.GONE, null)
    class SUCCESS<T>(products: List<Product>) : ProductListUiState<T>(View.GONE, View.VISIBLE, products, View.GONE, null)
    class ERROR<T>(message: String) : ProductListUiState<T>(View.GONE, View.GONE, emptyList(), View.VISIBLE, message)
}