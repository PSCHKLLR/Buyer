package com.example.buyer.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class Book(
    val bookId: String = "",
    val bookTitle: String = "",
    @DrawableRes val bookImg: Int = 0,
    @StringRes val bookDes: Int = 0,
    val bookAuthor: String = "",
    val bookGenre: String = "",
    val quantity: Int = 0,
    val price: Double = 0.0
)
