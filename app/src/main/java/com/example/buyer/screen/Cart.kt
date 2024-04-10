package com.example.buyer.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buyer.Navigation
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book
import com.example.buyer.model.CartItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyCart(cartList: ArrayList<CartItem>, navController: NavController, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier) {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable { navController.navigateUp() }
                            .align(Alignment.CenterVertically),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                    Row {
                        Text(
                            text = "Cart",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp)
                        )
                        Icon(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = null
                        )
                    }
                    Icon(
                        modifier = Modifier
                            .clickable { }
                            .align(Alignment.CenterVertically),
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null
                    )

                }
            },
            bottomBar = {
                Row(
//                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom) {
                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(80.dp)
                            .background(
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(
                                    topStart = 8.dp,
                                    topEnd = 8.dp
                                )
                            )
                    ) {
                        Row {
                            Text(
                                text = "RM${total(cartList)}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                            Button(
                                onClick = {
                                   navController.navigate(Navigation.Checkout.name)
                                },
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(horizontal = 16.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ShoppingCartCheckout,
                                    contentDescription = "checkout",
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    text = "Checkout",
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }
                    }
                }
            }
        ){
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(state = scrollState)
            ){
                if (cartList.isEmpty()){
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 280.dp)
                            .alpha(.3f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = null
                        )
                        Text(
                            text = "krik krik...",
                            fontSize = 20.sp,
                        )
                    }
                } else{
                    cartList.forEach {
                        ItemCheck(
                            navController = navController,
                            cartItem = it,
                            modifier = Modifier.height(200.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCheck(navController: NavController, cartItem: CartItem, modifier: Modifier = Modifier){
//    val mContext = LocalContext.current
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .clickable {
//                    mContext.startActivity(Intent(mContext, BookDescription::class.java))
                    navController.navigate(Navigation.BookDescription.name)
                }
        ) {
            Box(modifier = Modifier.padding(8.dp)) {
                Image(
                    painterResource(cartItem.book.bookImg),
                    contentDescription = "books",
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(3/4f)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds,
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Text(
                    text = cartItem.book.bookTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = cartItem.book.bookAuthor,
                )
                Text(
                    text = "RM${cartItem.book.price}"
                )
                Text(
                    text = "Quantity: ${cartItem.quantity}"
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PrevCart(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val bookList: List<Book> = Datasource().loadBooks()
    val cartList = ArrayList<CartItem>()
    cartList.add(
        CartItem(bookList[0], 1)
    )
    cartList.add(
        CartItem(bookList[1], 3)
    )
    cartList.add(
        CartItem(bookList[2], 1)
    )
    MyCart(cartList, navController = navController, modifier = Modifier.fillMaxSize())
}

fun total(cartList: ArrayList<CartItem>): Double{
    var total = 0.00
    cartList.forEach {
        total += (it.book.price * it.quantity)
    }
    return total
}