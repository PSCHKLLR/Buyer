package com.example.buyer.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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
import com.example.buyer.model.Transaction
import java.text.NumberFormat
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyCart(transaction: Transaction, navController: NavController, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    var isEnabled by remember {
        mutableStateOf(true)
    }
//    var total by remember {
//        mutableStateOf(transaction.total)
//    }
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
                                text = NumberFormat.getCurrencyInstance(Locale("ms", "MY")).format(transaction.calSubtotal()),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                            Button(
                                onClick = {
                                   navController.navigate(Navigation.Checkout.name)
                                },
                                enabled = isEnabled,
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(horizontal = 16.dp)
                                    .then(
                                        if (isEnabled) Modifier else Modifier.alpha(.5f)
                                    ),
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
                                isEnabled = transaction.cartList.isNotEmpty()
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
                if (transaction.cartList.isEmpty()){
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
                    transaction.cartList.forEach {item ->
                        ItemCheck(
                            navController = navController,
                            cartItem = item,
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
                        .aspectRatio(3 / 4f)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = cartItem.book.bookTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = cartItem.book.bookAuthor,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row (
                    modifier =Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = NumberFormat.getCurrencyInstance(Locale("ms", "MY")).format(cartItem.book.price),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    var count by remember {
                        mutableStateOf(cartItem.quantity)
                    }
                    cartItem.quantity = count
                    Stepper(count = count, onCountChanged = { count = it})
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PrevCart(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val bookList: List<Book> = Datasource().loadBooks()
    val transaction = Transaction()
    transaction.cartList.add(
        CartItem(bookList[0], 1)
    )
    transaction.cartList.add(
        CartItem(bookList[1], 3)
    )
    transaction.cartList.add(
        CartItem(bookList[2], 1)
    )
    MyCart(transaction = transaction, navController = navController, modifier = Modifier.fillMaxSize())
}

@Composable
fun Stepper(count: Int, onCountChanged: (Int) -> Unit) {
//    var count = cartItem.quantity
    val onRemove =  { onCountChanged(count - 1)}
    val onAdd =  { onCountChanged(count + 1)}
    var isEnabled by remember {
        mutableStateOf(true)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(4.dp))
            .height(24.dp)
    ){
        IconButton(
            onClick = if (count > 1) onRemove else {{}},
            enabled = isEnabled,
            modifier = Modifier
                .size(24.dp)
                .then(
                    if(isEnabled) Modifier else Modifier.alpha(.5f)
                )
        ) {
            Icon(imageVector = Icons.Filled.Remove, contentDescription = null,
                modifier = Modifier.size(16.dp))
            isEnabled = count != 1
        }
        VerticalDivider()
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(24.dp)
                .aspectRatio(1 / 1f)
        ) {
            Text(text = "$count")
        }
        VerticalDivider()
        IconButton(
            onClick =  onAdd ,
            modifier = Modifier
                .size(24.dp)
                .aspectRatio(1 / 1f)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null,
                modifier = Modifier.size(16.dp))
        }
    }
}