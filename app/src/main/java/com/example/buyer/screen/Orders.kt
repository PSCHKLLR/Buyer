package com.example.buyer.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.buyer.model.CartItem
import com.example.buyer.model.Transaction
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyerOrders(
    buyerViewModel: BuyerViewModel = viewModel(),
    orderList: ArrayList<Transaction>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val buyerUiState by buyerViewModel.uiState.collectAsState()
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    buyerUiState.orderList = orderList
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
                        text = "Orders",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        imageVector = Icons.Filled.Inventory,
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
                .verticalScroll(state = scrollState),
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(buyerUiState.orderList.isEmpty()){
                Text(text = "Krikk.. Krikk..")
            }else{
                buyerUiState.orderList.forEach {
                    Row (
                        modifier = Modifier.clickable {
                            showSheet = true
                        }
                    ){
                        OrderCard(transaction = it)
                    }
                    HorizontalDivider()
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
                    ){
                        Text(
                            text = "Order Details",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(4.dp))
                        buyerUiState.orderList[0].cartList.forEach {
                            ItemCheck(
                                navController = navController,
                                isRemove = false,
                                cartItem = it,
                                modifier = Modifier.height(160.dp),
                                active = false
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

        }
    }
}

@Composable
fun OrderCard(
    transaction: Transaction
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(shape = RectangleShape, color = Color(0xFFCFD8DC))
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center
    ){
        Row {
            Text(
                text = "Order:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(.4f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = transaction.transactionId)
        }
        Row {
            Text(
                text = "Ordered On:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(.4f)

            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "${SimpleDateFormat("dd MMM yyyy").format(transaction.dateOrdered)}")
        }
        Row {
            Text(
                text = "Items:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(.4f)

            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "${transaction.cartList.size}")
        }
        Row {
            Text(
                text = "Total:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(.4f)

            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = NumberFormat.getCurrencyInstance(Locale("ms", "MY")).format(transaction.total))
        }
        Row {
            Text(
                text = "Status:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(.4f)

            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = transaction.status)
        }
    }
}