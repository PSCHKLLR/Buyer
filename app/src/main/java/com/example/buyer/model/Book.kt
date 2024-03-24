package com.example.buyer.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Book(
    @StringRes val bookTitle: Int,
    @DrawableRes val bookImg: Int,
    @StringRes val bookDes: Int,
    var isFav: Boolean = false,
    val quantity: Int,
    val price: Double
)
