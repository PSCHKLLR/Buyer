package com.example.buyer.screen

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buyer.model.CartItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Checkout(cartList: ArrayList<CartItem>, navController: NavController, modifier: Modifier = Modifier) {
    Scaffold (
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
                        text = "Checkout",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        imageVector = Icons.Filled.ShoppingCartCheckout,
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
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Shipping",
                    fontWeight = FontWeight.SemiBold
                )
                Column {
                    Text(
                        text = "Standard Delivery",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "DELIVERY - FREE",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Name",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Address",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Payment Method",
                    fontWeight = FontWeight.SemiBold
                )
                Column {
                    Text(
                        text = "Select payment method",
                        textAlign = TextAlign.Right,
                    )
                }
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Billing Address",
                    fontWeight = FontWeight.SemiBold
                )
                Column {
                    Text(
                        text = "NAME",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "ADDRESS",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            HorizontalDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Subtotal",
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "RM69.69",
                        textAlign = TextAlign.Right
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tax",
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "RM69.69",
                        textAlign = TextAlign.Right
                    )
                }
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "RM69.69",
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.SemiBold
                )
            }
            HorizontalDivider()
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevCheck() {
    var cartList = ArrayList<CartItem>()
    val navController = rememberNavController()
    Checkout(cartList = cartList, navController = navController, modifier = Modifier.fillMaxSize())
}