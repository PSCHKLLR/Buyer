package com.example.buyer.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.buyer.Navigation
import com.example.buyer.model.Transaction
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Checkout(
    buyerViewModel: BuyerViewModel = viewModel(),
    transaction: Transaction,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    val buyerUiState by buyerViewModel.uiState.collectAsState()
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
//    val scope = rememberCoroutineScope()
    var option = ""
    var isEnabled by remember { mutableStateOf(false) }
    var selectShip by remember { mutableStateOf(false) }
    var selectPay by remember { mutableStateOf(false) }


    buyerViewModel.updateTransaction(transaction)
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
        },
        bottomBar = {
            Box (
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Button(
                    onClick = {
                        buyerUiState.currentTransaction.dateOrdered = Date()
                        buyerUiState.orderList.add(buyerUiState.currentTransaction)
                        buyerUiState.currentTransaction = Transaction()
                        option = ""
                        navController.navigate(Navigation.Home.name)
                              },
                    enabled = isEnabled,
                    modifier = Modifier
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "Place order",
                        modifier = Modifier.padding(4.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.padding(4.dp)
                    )
                    isEnabled = selectShip && selectPay
                }
            }
        }
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showSheet = true
                        option = "Shipping"
                    }
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Shipping",
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(88.dp))
                Column {
                    Text(
                        text = buyerUiState.currentTransaction.shipping,
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    selectShip = buyerUiState.currentTransaction.shipping != "Select Shipping Method"
                    Spacer(modifier = Modifier.height(12.dp))
                    var fee: String
                    if (buyerUiState.currentTransaction.deliveryFee <= 0.0){
                        fee = "FREE"
                    } else {
                        fee = NumberFormat.getCurrencyInstance(Locale("ms", "MY")).format(buyerUiState.currentTransaction.deliveryFee)
                    }
                    Text(
                        text = "DELIVERY - ${fee}",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "MUHAMMAD RIDWAN",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "1072, JALAN INDAH 8, KAMPUNG AMPANG INDAH, 68000 AMPANG, SELANGOR",
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
                        showSheet = true
                        option = "Payment"
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
                        text = buyerUiState.currentTransaction.paymentMethod,
                        textAlign = TextAlign.Right,
                    )
                    selectPay = buyerUiState.currentTransaction.paymentMethod != "Select Payment Method"
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
                Spacer(modifier = Modifier.width(48.dp))
                Column {
                    Text(
                        text = "MUHAMMAD RIDWAN",
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "1072, JALAN INDAH 8, KAMPUNG AMPANG INDAH, 68000 AMPANG, SELANGOR",
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
                        text = NumberFormat.getCurrencyInstance(Locale("ms", "MY")).format(buyerUiState.currentTransaction.calSubtotal()),
                        textAlign = TextAlign.Right
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
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
                        text = NumberFormat.getCurrencyInstance(Locale("ms", "MY")).format(buyerUiState.currentTransaction.calTax()),
                        textAlign = TextAlign.Right
                    )
                }
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showSheet = true
                        option = "Voucher"
                    }
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Voucher",
                    fontWeight = FontWeight.SemiBold
                )
                Column {
                    var discount: String = " - " + NumberFormat.getCurrencyInstance(Locale("ms", "MY")).format(buyerUiState.currentTransaction.discount)
                    if (buyerUiState.currentTransaction.discount <= 0.0) {
                        discount = ""
                    }
                    Text(
                        text = buyerUiState.currentTransaction.voucher + discount,
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
                    text = "Total",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = NumberFormat.getCurrencyInstance(Locale("ms", "MY")).format(buyerUiState.currentTransaction.calTotal()),
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.SemiBold
                )
            }
            if (showSheet){
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    sheetState = sheetState,
                    properties = ModalBottomSheetProperties(
                        isFocusable = true,
                        shouldDismissOnBackPress = true,
                        securePolicy = SecureFlagPolicy.SecureOff
                    ),
                    dragHandle = {},
                    shape = RectangleShape
                ) {
                    if (option == "Shipping") {
                        Shipping(buyerViewModel = buyerViewModel)
                    }else if (option == "Voucher") {
                        Voucher(buyerViewModel = buyerViewModel)
                    } else if (option == "Payment"){
                        PaymentMethod(buyerViewModel = buyerViewModel)
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevCheck() {
    val navController = rememberNavController()
    val transaction = Transaction()

    Checkout(transaction = transaction, navController = navController, modifier = Modifier.fillMaxSize())
}

@Composable
fun Shipping(buyerViewModel: BuyerViewModel = viewModel()) {
    var standard by remember { mutableStateOf(false) }
    var fast by remember { mutableStateOf(false) }
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

    ){
        Text(
            text = "Shipping Method",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        HorizontalDivider()
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Button(
                onClick = {
                    buyerViewModel.shipping("Standard Delivery")
                    standard = true
                    fast = false
                },
                shape = RectangleShape,
                colors = ButtonColors(
                    containerColor = Color(0xFFCFD8DC),
                    contentColor = Color.Black,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Standard Delivery")
            }
            VerticalDivider(
                thickness = 4.dp,
                color = Color.Black,
                modifier = Modifier
                    .height(28.dp)
                    .then(
                        if (standard) Modifier.alpha(1f) else Modifier.alpha(0f)
                    )
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Button(
                onClick = {
                    buyerViewModel.shipping("Fast Delivery")
                    standard = false
                    fast = true
                },
                shape = RectangleShape,
                colors = ButtonColors(
                    containerColor = Color(0xFFCFD8DC),
                    contentColor = Color.Black,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Fast Delivery")
            }
            VerticalDivider(
                thickness = 4.dp,
                color = Color.Black,
                modifier = Modifier
                    .height(28.dp)
                    .then(
                        if (fast) Modifier.alpha(1f) else Modifier.alpha(0f)
                    )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PaymentMethod(buyerViewModel: BuyerViewModel = viewModel()) {
    var cash by remember { mutableStateOf(false) }
    var online by remember { mutableStateOf(false) }
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        ){
        Text(
            text = "Payment Method",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        HorizontalDivider()
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Button(
                onClick = {
                    buyerViewModel.payment("Cash On Delivery")
                    cash = true
                    online = false
                },
                shape = RectangleShape,
                colors = ButtonColors(
                    containerColor = Color(0xFFCFD8DC),
                    contentColor = Color.Black,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Cash On Delivery")
            }
            VerticalDivider(
                thickness = 4.dp,
                color = Color.Black,
                modifier = Modifier
                    .height(28.dp)
                    .then(
                        if (cash) Modifier.alpha(1f) else Modifier.alpha(0f)
                    )
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Button(
                onClick = {
                    buyerViewModel.payment("Online Banking")
                    cash = false
                    online = true
                },
                shape = RectangleShape,
                colors = ButtonColors(
                    containerColor = Color(0xFFCFD8DC),
                    contentColor = Color.Black,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Online Banking")
            }
            VerticalDivider(
                thickness = 4.dp,
                color = Color.Black,
                modifier = Modifier
                    .height(28.dp)
                    .then(
                        if (online) Modifier.alpha(1f) else Modifier.alpha(0f)
                    )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Voucher(buyerViewModel: BuyerViewModel = viewModel()) {
    val buyerUiState by buyerViewModel.uiState.collectAsState()
    var five by remember { mutableStateOf(false) }
    var ten by remember { mutableStateOf(false) }
    var twenty by remember { mutableStateOf(false) }
    var fifty by remember { mutableStateOf(false) }
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        ){
        Text(
            text = "Voucher",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        HorizontalDivider()
        Spacer(modifier = Modifier.height(4.dp))
        if (buyerUiState.user.five_percent > 0){
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Button(
                    onClick = {
                        buyerViewModel.calDisc("5% Discount")
                        five = true
                        ten = false
                        twenty = false
                        fifty = false
                    },
                    shape = RectangleShape,
                    colors = ButtonColors(
                        containerColor = Color(0xFFCFD8DC),
                        contentColor = Color.Black,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "5% Discount")
                }
                VerticalDivider(
                    thickness = 4.dp,
                    color = Color.Black,
                    modifier = Modifier
                        .height(28.dp)
                        .then(
                            if (five) Modifier.alpha(1f) else Modifier.alpha(0f)
                        )
                )
            }
        }
        if (buyerUiState.user.ten_percent > 0){
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Button(
                    onClick = {
                        buyerViewModel.calDisc("10% Discount")
                        five = false
                        ten = true
                        twenty = false
                        fifty = false
                    },
                    shape = RectangleShape,
                    colors = ButtonColors(
                        containerColor = Color(0xFFCFD8DC),
                        contentColor = Color.Black,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.Gray
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "10% Discount")
                }
                VerticalDivider(
                    thickness = 4.dp,
                    color = Color.Black,
                    modifier = Modifier
                        .height(28.dp)
                        .then(
                            if (ten) Modifier.alpha(1f) else Modifier.alpha(0f)
                        )
                )
            }
        }
        if (buyerUiState.user.twenty_percent > 0){
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Button(
                    onClick = {
                        buyerViewModel.calDisc("20% Discount")
                        five = false
                        ten = false
                        twenty = true
                        fifty = false
                    },
                    shape = RectangleShape,
                    colors = ButtonColors(
                        containerColor = Color(0xFFCFD8DC),
                        contentColor = Color.Black,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "20% Discount")
                }
                VerticalDivider(
                    thickness = 4.dp,
                    color = Color.Black,
                    modifier = Modifier
                        .height(28.dp)
                        .then(
                            if (twenty) Modifier.alpha(1f) else Modifier.alpha(0f)
                        )
                )
            }
        }
        if (buyerUiState.user.fifty_ringgit > 0){
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Button(
                    onClick = {
                        buyerViewModel.calDisc("RM50 Discount")
                        five = false
                        ten = false
                        twenty = false
                        fifty = true
                    },
                    shape = RectangleShape,
                    colors = ButtonColors(
                        containerColor = Color(0xFFCFD8DC),
                        contentColor = Color.Black,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.Gray
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "RM50 Discount")
                }
                VerticalDivider(
                    thickness = 4.dp,
                    color = Color.Black,
                    modifier = Modifier
                        .height(28.dp)
                        .then(
                            if (fifty) Modifier.alpha(1f) else Modifier.alpha(0f)
                        )
                )
            }
        }
        if (buyerUiState.user.five_percent + buyerUiState.user.ten_percent + buyerUiState.user.twenty_percent + buyerUiState.user.fifty_ringgit == 0 ){
            Text(text = "No Voucher Available",
                modifier = Modifier.alpha(.4f))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}