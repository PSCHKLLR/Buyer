package com.example.buyer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book
import com.example.buyer.screen.Description
import com.example.buyer.screen.Home
import com.example.buyer.screen.MyCart
import com.example.buyer.screen.Search

enum class Navigation {
    Home,
    BookDescription,
    Cart,
    Search,
    TradeIn,
    Wishlist
}

@Composable
fun Navigate(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Navigation.Home.name
    ) {
        composable(route = Navigation.Home.name){
            Home(navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(route = Navigation.BookDescription.name){
            val bookList: List<Book> = Datasource().loadBooks()
            Description(navController = navController,bookList[0], modifier = Modifier.fillMaxSize())
        }
        composable(route = Navigation.Cart.name){
            MyCart(navController = navController,modifier = Modifier.fillMaxSize())
        }
        composable(route = Navigation.Search.name){
            Search(modifier = Modifier.fillMaxSize())
        }

    }
}