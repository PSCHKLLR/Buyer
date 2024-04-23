package com.example.buyer.screen

import androidx.lifecycle.ViewModel
import com.example.buyer.model.Book
import com.example.buyer.model.CartItem
import com.example.buyer.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BuyerViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(BuyerUiState())
    val uiState: StateFlow<BuyerUiState> = _uiState.asStateFlow()

    //CART
    fun cartTotal(){
        var total = 0.00
        uiState.value.currentOrder.cartList.forEach {
            total += (it.book.price * it.quantity)
        }
        updateCartState(updatedTotal = total)
    }

    fun addToCart(cartItem: CartItem){
        val transaction = uiState.value.currentOrder
        var isExisted = false
        transaction.cartList.asReversed().forEach {
            if (it.book.bookId.equals(cartItem.book.bookId)){
                it.updateQuantity(it.quantity+1)
                isExisted = true
            }
        }
        if (!isExisted){
            transaction.cartList.add(cartItem)
        }
        updateTransaction(transaction)
    }

    fun removeFromCart(cartItem: CartItem){
        val transaction = uiState.value.currentOrder
        val cartList = ArrayList<CartItem>()
        transaction.cartList.forEach {
            if (!it.book.bookId.equals(cartItem.book.bookId)){
                cartList.add(it)
            }
        }
        transaction.cartList = cartList
        updateTransaction(transaction)

    }
    
    private fun updateCartState(updatedTotal: Double){
        _uiState.update {
            it.copy(
                cartTotal = updatedTotal
            )
        }
    }



    //WISHLIST
    fun addToWishlist(book: Book){
        val wishlist = uiState.value.wishList
        wishlist.add(book)
        _uiState.update {
            it.copy(
                wishList = wishlist
            )
        }
    }

    fun removeFromWishlist(book: Book){
        val wishlist = uiState.value.wishList
        wishlist.remove(book)
        _uiState.update {
            it.copy(
                wishList = wishlist
            )
        }
    }


    //Checkout

    fun shipping(method: String){
        val transaction = uiState.value.currentOrder
        transaction.shipping = method
        transaction.deliveryFee = when(method){
            "Fast Delivery" -> 4.8
            else -> 0.0
        }
        updateTransaction(transaction)
    }

    fun payment(method: String){
        val transaction = uiState.value.currentOrder
        transaction.paymentMethod = method
        updateTransaction(transaction)
    }

    fun updateTransaction(order: Order){
        _uiState.update {
            it.copy(
                currentOrder = order
            )
        }
    }

    fun calDisc(voucher: String) {
        val transaction = uiState.value.currentOrder
        transaction.voucher = voucher
        transaction.discount = when(voucher){
            "5% Discount" -> transaction.calSubtotal() * .05
            "10% Discount" -> transaction.calSubtotal() * .1
            "20% Discount" -> transaction.calSubtotal() * .2
            else -> 50.0
        }
        updateTransaction(transaction)
    }
}