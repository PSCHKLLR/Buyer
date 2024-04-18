package com.example.buyer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.buyer.model.Book
import com.example.buyer.model.Transaction
import com.example.buyer.screen.BuyerOrders
import com.example.buyer.screen.BuyerViewModel
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
fun Navigate(
    buyerViewModel: BuyerViewModel = viewModel()
){
    val buyerUiState by buyerViewModel.uiState.collectAsState()
    val navController = rememberNavController()
    val orderList = ArrayList<Transaction>()
    NavHost(
        navController = navController,
        startDestination = Navigation.Home.name
    ) {
        composable(route = Navigation.Home.name){
            Home(navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(
            route = Navigation.BookDescription.name + "/{BookId}",
            arguments = listOf(
                navArgument("BookId"){
                    type = NavType.StringType
                },
//                navArgument("User"){
//                    type = NavType.StringType
//                }
            )
        ){
            Description(
                buyerViewModel = buyerViewModel,
                bookId = it.arguments?.getString("BookId")!!,
//                userId = it.arguments?.getString("User")!!,
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = Navigation.Search.name){

            Search(viewModel = SearchViewModel(), navController = navController)
        }
        composable(route = Navigation.TradeIn.name){

            TradeIn(navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(route = Navigation.Wishlist.name){

            WishList(
                bookList = buyerUiState.wishList,
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Navigation.Cart.name){

            MyCart(
                cartViewModel = buyerViewModel,
                transaction = buyerUiState.transaction,
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Navigation.Checkout.name){

            Checkout(buyerUiState.transaction, navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(route = Navigation.Orders.name){

            BuyerOrders(
                buyerViewModel = buyerViewModel,
                orderList = orderList,
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }

//        navigation(
//            startDestination = Navigation.Home.name,
//            route = "CheckOut"
//        ) {
//            composable(route = Navigation.Home.name){
//                val viewModel = it.sharedViewModel<SampleViewModel>(navController)
//                Home(viewModel,navController = navController, modifier = Modifier.fillMaxSize())
//            }
//
//        }
    }
}

