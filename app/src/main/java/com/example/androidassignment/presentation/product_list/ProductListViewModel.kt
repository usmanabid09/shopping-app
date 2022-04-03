package com.example.androidassignment.presentation.product_list

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidassignment.MainApplication
import com.example.androidassignment.common.State
import com.example.androidassignment.domain.model.Product
import com.example.androidassignment.domain.use_case.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    application: MainApplication,
    private val getProductListUseCase: GetProductListUseCase
) : AndroidViewModel(application) {

    private val _productListUiState: MutableStateFlow<ProductListUiState> = MutableStateFlow(ProductListUiState.LOADING())
    val productListUiState = _productListUiState.asStateFlow()

    private val _onProductClick: MutableLiveData<Product> = MutableLiveData()
    val productClick: LiveData<Product> = _onProductClick

    init {
        getProductList()
    }

    private fun getProductList() {
        getProductListUseCase().onEach { state ->
            when (state) {
                is State.Loading -> {
                    _productListUiState.update { ProductListUiState.LOADING() }
                }
                is State.Error -> {
                    _productListUiState.update { ProductListUiState.ERROR(state.message!!) }
                }
                is State.Success -> {
                    _productListUiState.update { ProductListUiState.SUCCESS(state.data!!) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onProductItemClick(product: Product) {
        _onProductClick.value = product
    }

}