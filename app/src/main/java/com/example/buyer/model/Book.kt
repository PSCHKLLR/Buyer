package com.example.buyer.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Book(
    val bookTitle: String,
    @DrawableRes val bookImg: Int,
    @StringRes val bookDes: Int,
    val bookAuthor: String,
    val bookGenre: String,
    val quantity: Int,
    val price: Double
)
