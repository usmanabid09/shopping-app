package com.example.androidassignment.presentation.product_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidassignment.databinding.FragmentProductListBinding
import com.example.androidassignment.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var productListAdapter: ProductListAdapter
    private var productList: ArrayList<Product> = arrayListOf()
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private val productListViewModel: ProductListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductListBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                observeStateFlowData()
            }
        }
    }

    private fun initialize() {
        productListAdapter = ProductListAdapter(productList)
        productListAdapter.onItemClick = { product -> navigateToProductScreen(product) }
        binding.recyclerViewProductList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewProductList.adapter = productListAdapter
    }

    private suspend fun observeStateFlowData() {
        productListViewModel.productListUiState.collect { uiState: ProductListUiState -> updateScreen(uiState) }
    }

    private fun updateScreen(uiState: ProductListUiState) {
        binding.textViewLoading.visibility = uiState.progressVisibility
        binding.recyclerViewProductList.visibility = uiState.productListVisibility
        binding.textViewError.visibility = uiState.errorVisibility
        if (!uiState.error.isNullOrBlank()) binding.textViewError.text = uiState.error
        if (uiState.productList != null) updateProductList(uiState.productList)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateProductList(productList: List<Product>) {
        this.productList.clear()
        this.productList.addAll(productList)
        productListAdapter.notifyDataSetChanged()
    }

    private fun navigateToProductScreen(product: Product) {
        findNavController().navigate(ProductListFragmentDirections.productListFragmentToProductFragment(product))
    }

}