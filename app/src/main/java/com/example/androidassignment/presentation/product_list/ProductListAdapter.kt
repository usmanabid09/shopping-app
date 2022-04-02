package com.example.androidassignment.presentation.product_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassignment.R
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
        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewProductTitle)

        fun bindProductToView(product: Product) {
            textViewTitle.text = product.title

            itemView.setOnClickListener { onItemClick?.invoke(product) }
        }
    }

}