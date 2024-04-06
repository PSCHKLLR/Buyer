package com.example.buyer.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buyer.Navigation
import com.example.buyer.R
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyCart(navController: NavController, modifier: Modifier = Modifier) {
    val bookList: List<Book> = Datasource().loadBooks()
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
                            .clickable {  }
                            .align(Alignment.CenterVertically),
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null
                    )

                }
            },
            content = {
                Column (modifier = Modifier
                    .padding(top = 64.dp)
                    .verticalScroll(state = scrollState)
                ){
                    ItemCheck(navController = navController,book = bookList[0], modifier = Modifier.height(200.dp))
                    ItemCheck(navController = navController,book = bookList[0], modifier = Modifier.height(200.dp))
                    ItemCheck(navController = navController,book = bookList[0], modifier = Modifier.height(200.dp))
                }
            },
            bottomBar = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom) {
                    Column {
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White)
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Subtotal")
                                Text(text = "RM125.00")
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Tax")
                                Text(text = "RM125.00")
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Total",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "RM125.00",
                                    fontWeight = FontWeight.Bold
                                    )
                            }
                        }
                        Box(
                            contentAlignment = Alignment.Center,
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
                            Button(
                                onClick = {
                                    /*TODO*/
                                },
                                modifier = Modifier
                                    .height(50.dp),
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
        )
    }
}

@Composable
fun ItemCheck(navController: NavController, book: Book, modifier: Modifier = Modifier){
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
                    painterResource(book.bookImg),
                    contentDescription = "books",
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillHeight,
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Text(
                    text = LocalContext.current.getString(book.bookTitle),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = LocalContext.current.getString(R.string.author1),
                )
                Text(
                    text = "RM125.00"
                )
                Text(
                    text = "Quantity: 1"
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PrevCart(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    MyCart(navController = navController, modifier = Modifier.fillMaxSize())
}