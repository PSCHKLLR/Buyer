package com.example.buyer.model

import java.text.NumberFormat

class Transaction(
    val transactionId: String = "",
    var cartList: ArrayList<CartItem> = ArrayList(),
    var paymentMethod: String = "",
    var status: String = ""
) {


    fun calSubtotal(): Double{
        var total = 0.00
        cartList.forEach {
            total += (it.book.price * it.quantity)
        }
        return total
    }

    fun calTax(): Double{
        return calSubtotal() * 0.06
    }

    fun calTotal(): Double {
        return calSubtotal() + calTax()
    }
}