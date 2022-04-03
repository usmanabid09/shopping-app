package com.example.androidassignment.presentation.product_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidassignment.common.loadImage
import com.example.androidassignment.databinding.FragmentProductBinding
import com.example.androidassignment.domain.model.Product
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : BottomSheetDialogFragment() {

    private val viewModel: ProductViewModel by viewModels()
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.product.observe(viewLifecycleOwner) { product -> setUiState(product) }
    }

    private fun setUiState(product: Product) {
        binding.imageViewProductPicture.loadImage(product.picture)
        binding.textViewProductTitle.text = product.title
        binding.textViewProductPrice.text = product.price.toString()
        binding.buttonAddToCart.setOnClickListener {
            Toast.makeText(requireContext(), "${product.title} has been added in Cart", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

}