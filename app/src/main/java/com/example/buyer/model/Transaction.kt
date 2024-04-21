package com.example.buyer.model

import java.text.NumberFormat
import java.util.Date

class Transaction(
    var transactionId: String = "",
    var cartList: ArrayList<CartItem> = ArrayList(),
    var paymentMethod: String = "Select Payment Method",
    var status: String = "Preparing",
    var shipping: String = "Select Shipping Method",
    var deliveryFee: Double = 0.0,
    var discount: Double = 0.0,
    var voucher: String = "Select Voucher",
    var dateOrdered: Date = Date(),
    var total: Double = 0.0
) {
    init {
        transactionId = transID()
    }
    private fun transID() : String{
        var id = ""


        return id
    }

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
        total = calSubtotal() + calTax() + deliveryFee - discount
        return calSubtotal() + calTax() + deliveryFee - discount
    }
}