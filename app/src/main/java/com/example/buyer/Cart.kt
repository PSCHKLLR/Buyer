package com.example.buyer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book

class Cart : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ShoppingCart()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyCart(modifier: Modifier = Modifier) {
val bookList: List<Book> = Datasource().loadBooks()
    
    Column(modifier = modifier) {
        Scaffold(
            content = {
                Column (modifier = Modifier.padding(top = 64.dp)){
                    Text(
                        text = "Cart",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    ItemCheck(book = bookList[0], modifier = Modifier.height(200.dp))
                }
            },
            bottomBar = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom) {
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
        )
    }
}

@Composable
fun ItemCheck(book: Book, modifier: Modifier = Modifier){
    val mContext = LocalContext.current
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .clickable {
                    mContext.startActivity(Intent(mContext, BookDescription::class.java))
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShoppingCart() {
    MyCart(modifier = Modifier.fillMaxSize())
}