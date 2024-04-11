package com.example.buyer.model

import java.text.NumberFormat

class Transaction {
    val cartList = ArrayList<CartItem>()
    var paymentMethod: String = ""
    var status: String = ""
    fun calSubtotal(): Double{
        var total = 0.00
        cartList.forEach {
            total += (it.book.price * it.quantity)
        }
        return total
    }

    fun calTax(): Double{
        var subttl = calSubtotal()
        return subttl * 0.06
    }

    fun calTotal(): Double {
        return calSubtotal() - calTax()
    }
}