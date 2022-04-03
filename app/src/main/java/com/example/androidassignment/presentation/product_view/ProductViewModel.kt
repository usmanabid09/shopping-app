package com.example.androidassignment.presentation.product_view

import androidx.lifecycle.*
import com.example.androidassignment.MainApplication
import com.example.androidassignment.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: MainApplication,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    companion object {
        const val PRODUCT = "product"
    }

    private val _product: MutableLiveData<Product> = MutableLiveData()
    val product: LiveData<Product> = _product

    init {
        val argProduct = savedStateHandle.get<Product>(PRODUCT)!!
        _product.value = argProduct
    }

}