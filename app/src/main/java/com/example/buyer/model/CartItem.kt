package com.example.buyer.model

class CartItem(
    val book: Book,
    var quantity: Int
){
    fun updateQuantity(quantity: Int) {
        this.quantity = quantity
    }
}
