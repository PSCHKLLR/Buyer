package com.example.buyer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book
import com.example.buyer.model.CartItem
import com.example.buyer.model.Transaction
import com.example.buyer.screen.BuyerOrders
import com.example.buyer.screen.Checkout
import com.example.buyer.screen.Description
import com.example.buyer.screen.Home
import com.example.buyer.screen.MyCart
import com.example.buyer.screen.Search
import com.example.buyer.screen.SearchViewModel
import com.example.buyer.screen.TradeIn
import com.example.buyer.screen.WishList

enum class Navigation {
    Home,
    BookDescription,
    Cart,
    Search,
    TradeIn,
    Wishlist,
    Checkout,
    Orders
}

@Composable
fun Navigate(){
    val bookList: List<Book> = Datasource().loadBooks() //Temp
    val trans = Transaction()
    trans.cartList.add(
        CartItem(bookList[0], 1)
    )
    trans.cartList.add(
        CartItem(bookList[1], 3)
    )
    trans.cartList.add(
        CartItem(bookList[2], 1)
    )
    var bookArray = ArrayList<Book>() //Temp
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Navigation.Home.name
    ) {
        composable(route = Navigation.Home.name){
            Home(navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(
            route = Navigation.BookDescription.name
        ){
            Description(book = bookList[0], navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(route = Navigation.Cart.name){
            MyCart(trans, navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(route = Navigation.Search.name){
            Search(viewModel = SearchViewModel(), navController = navController)
        }
        composable(route = Navigation.TradeIn.name){
            TradeIn(navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(route = Navigation.Wishlist.name){
            WishList(bookArray, navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(route = Navigation.Checkout.name){
            Checkout(trans, navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(route = Navigation.Orders.name){
            BuyerOrders(navController = navController, modifier = Modifier.fillMaxSize())
        }
    }
}