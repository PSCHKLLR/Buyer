package com.example.buyer.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buyer.model.Book

@Composable
fun WishList(
    bookList: ArrayList<Book>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
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
                        text = "Wishlist",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null
                    )
                }
                Icon(
                    modifier = Modifier
                        .alpha(0f)
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null
                )

            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            if(bookList.isEmpty()){
                Text(text = "Krik Krik...")
            }
        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PrevWish() {
    val bookList = ArrayList<Book>()
    val navController = rememberNavController()
    WishList(bookList = bookList, navController = navController, modifier = Modifier.fillMaxSize())
}