package com.example.androidassignment.presentation.product_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidassignment.R
import com.example.androidassignment.common.loadImage
import com.example.androidassignment.domain.model.Product

class ProductListAdapter(
    private val productList: List<Product>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((product: Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_product_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).bindProductToView(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewProductPicture: ImageView = itemView.findViewById(R.id.imageViewProductPicture)
        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewProductTitle)
        private val textViewProductShortDescription: TextView = itemView.findViewById(R.id.textViewProductShortDescription)

        fun bindProductToView(product: Product) {
            imageViewProductPicture.loadImage(product.picture)
            textViewTitle.text = product.title
            textViewProductShortDescription.text = product.shortDescription
            itemView.setOnClickListener { onItemClick?.invoke(product) }
        }
    }

}