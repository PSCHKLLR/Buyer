package com.example.buyer.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Book(
    @StringRes val stringResId: Int,
    @DrawableRes val imageResId: Int
)
