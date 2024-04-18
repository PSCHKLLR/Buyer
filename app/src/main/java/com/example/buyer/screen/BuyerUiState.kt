package com.example.buyer.screen

import com.example.buyer.model.Book
import com.example.buyer.model.CartItem
import com.example.buyer.model.Transaction

data class BuyerUiState(
    val cartTotal: Double = 0.0,
    val transaction: Transaction = Transaction(),
    val wishList: ArrayList<Book> = ArrayList()
)
