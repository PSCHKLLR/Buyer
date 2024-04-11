
package com.example.buyer.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buyer.data.Datasource
import com.example.buyer.model.Book
import java.text.NumberFormat
import java.util.Locale

@Composable
fun Description(book: Book, navController: NavController, modifier: Modifier = Modifier) {
    var isFav by remember {
        mutableStateOf(false)
    }

    val fav: ImageVector = when(isFav){
        false -> Icons.Filled.FavoriteBorder
        else -> Icons.Filled.Favorite
    }


    Column(modifier = modifier) {
        Box {
            Image(
                painterResource(book.bookImg),
                contentDescription = "Item",
                modifier = Modifier
                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Row (
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()){
                IconButton(
                    onClick = {
                        navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
                IconButton(onClick = {
                    if (!isFav){
                        isFav = true
                    } else{
                        isFav = false
                    }
                }) {
                    Icon(
                        imageVector = fav,
                        contentDescription = "wishList",
                        tint = Color.Red)
                }
            }
        }
        Row(modifier = Modifier
            .padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = book.bookTitle,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = NumberFormat.getCurrencyInstance(Locale("ms", "MY")).format(book.price),
                fontSize = 25.sp,
                fontWeight = FontWeight.Black,
                color = Color.Blue
            )
        }
        Text(
            text = book.bookAuthor,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
//        var rating by remember {
//            mutableStateOf(1f)
//        }
        Text(
            text = "DESCRIPTION",
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            modifier = Modifier.padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp
            )
        )
        Text(
            text = LocalContext.current.getString(book.bookDes),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        )
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
                        imageVector = Icons.Filled.AddShoppingCart,
                        contentDescription = "Add to Cart",
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = "Add to Cart",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BookDetail() {
    val bookList: List<Book> = Datasource().loadBooks()
    val navController = rememberNavController()
    Description( bookList[0],navController, modifier = Modifier.fillMaxSize())
}