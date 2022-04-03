package com.example.androidassignment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val title: String,
    val picture: String,
    val shortDescription: String,
    val longDescription: String,
    val price: Int
) : Parcelable