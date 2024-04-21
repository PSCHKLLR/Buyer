package com.example.buyer.screen

import com.example.buyer.model.Book
import com.example.buyer.model.TempUser
import com.example.buyer.model.Transaction

data class BuyerUiState(
    val cartTotal: Double = 0.0,
    var currentTransaction: Transaction = Transaction(),
    val wishList: ArrayList<Book> = ArrayList(),
    var orderList: ArrayList<Transaction> = ArrayList(),
    val user: TempUser = TempUser()
)
