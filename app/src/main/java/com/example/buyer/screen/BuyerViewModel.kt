package com.example.buyer.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book
import com.example.buyer.model.CartItem
import com.example.buyer.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BuyerViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(BuyerUiState())
    val uiState: StateFlow<BuyerUiState> = _uiState.asStateFlow()




    //CART
    fun calTotal(){
        var total = 0.00
        uiState.value.transaction.cartList.forEach {
            total += (it.book.price * it.quantity)
        }
        updateCartState(updatedTotal = total)
    }

    fun addToCart(cartItem: CartItem){
        val transaction = uiState.value.transaction
        var isExisted = false
        transaction.cartList.forEach {
            if (it.book.bookId.equals(cartItem.book.bookId)){
                it.updateQuantity(it.quantity+1)
                isExisted = true
            }
        }
        if (!isExisted){
            transaction.cartList.add(cartItem)
        }
        _uiState.update {
            it.copy(
                transaction = transaction
            )
        }
    }

    fun removeFromCart(cartItem: CartItem){
        val transaction = uiState.value.transaction
        transaction.cartList.forEach {
            if (it.book.bookId.equals(cartItem.book.bookId)){
                transaction.cartList.remove(it)
            }
        }
        _uiState.update {
            it.copy(
                transaction = transaction
            )
        }
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
}