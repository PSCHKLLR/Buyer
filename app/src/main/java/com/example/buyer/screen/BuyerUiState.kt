package com.example.buyer.screen

import com.example.buyer.model.Book
import com.example.buyer.model.TempUser
import com.example.buyer.model.Order

data class BuyerUiState(
    val cartTotal: Double = 0.0,
    var currentOrder: Order = Order(),
    val wishList: ArrayList<Book> = ArrayList(),
    var orderList: ArrayList<Order> = ArrayList(),
    val user: TempUser = TempUser(),
    var showOrder: Order = Order()
)
