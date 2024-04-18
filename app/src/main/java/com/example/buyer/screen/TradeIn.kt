@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.buyer.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


data class TradeIn(
    var title: String = "",
    var author: String = "",
    var condition: String = "",
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TradeIn(navController: NavController, modifier: Modifier = Modifier) {
    var popUp by remember { mutableStateOf(false) }
    val onClickOutside = { popUp = !popUp }


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
                        text = "Trade In",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        imageVector = Icons.Filled.SwapHoriz,
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = { popUp = true }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        }
    ){
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "krikk.. krikk.."
                )

            }
            if(popUp) {
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(10f)
                ){
                    Popup(
                        alignment = Alignment.Center,
                        properties = PopupProperties(
                            excludeFromSystemGesture = true,
                            focusable = true
                        ),
                        onDismissRequest = {

                        },
                        content = {
                            NewTrade(onClickOutside = { onClickOutside() })
                        }
                    )
                }
            }
        }
        
    }
}


@Composable
fun NewTrade(onClickOutside: () -> Unit) {
    val trade = TradeIn()
    val options = listOf("Like New", "Good", "Fair")
    var title by remember {
        mutableStateOf("")
    }
    var author by remember {
        mutableStateOf("")
    }
    var (selectedCondition, onOptionSelected) = remember { mutableStateOf((options[0])) }


    Box (
        modifier = Modifier
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xFFCFD8DC), shape = RoundedCornerShape(8.dp))
            .fillMaxHeight(.5f)
            .fillMaxWidth(.9f),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ){
            Text(
                text = "New Trade In",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    trade.title = title
                },
                label = {
                    Text(text = "Book's Title")
                },
                modifier = Modifier.fillMaxWidth(.9f),
                keyboardOptions = KeyboardOptions.Default

            )
            OutlinedTextField(
                value = author,
                onValueChange = {
                    author = it
                    trade.author = author
                },
                label = {
                    Text(text = "Book's Author")
                },
                modifier = Modifier.fillMaxWidth(.9f),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Book's Condition",
                modifier = Modifier.fillMaxWidth(.9f),
                textAlign = TextAlign.Start
            )
            Row(
                Modifier
                    .selectableGroup()
                    .fillMaxWidth(.9f),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                options.forEach {
                    Row(
                        Modifier
                            .selectable(
                                selected = (it == selectedCondition),
                                onClick = { onOptionSelected(it) },
                                role = Role.RadioButton
                            )
                    ) {
                        RadioButton(
                            selected = (it == selectedCondition),
                            onClick = null
                        )
                        Text(text = it)
                    }
                }
            }
//            ExposedDropdownMenuBox(
//                expanded = isExpanded,
//                onExpandedChange = {
//                    isExpanded = it
//                },
//                content = {
//                    OutlinedTextField(
//                        value = selectedCondition,
//                        onValueChange = { selectedCondition = it },
//                        modifier = Modifier.menuAnchor(),
//                        readOnly = true,
//                        label = {
//                            Text(text = "Book's Condition")
//                        }
//                    )
//                    ExposedDropdownMenu(
//                        expanded = isExpanded, onDismissRequest = { isExpanded = false }
//                    ) {
//                        options.forEach {
//                            DropdownMenuItem(
//                                text = {
//                                    Text(
//                                        text = it
//                                    )
//                                },
//                                onClick = {
//                                    selectedCondition = it
//                                    isExpanded = false
//                                    trade.condition = selectedCondition
//                                },
//                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
//                            )
//
//                        }
//                    }
//                }
//            )

            Row (
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = { onClickOutside() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Text(
                        text = "Cancel"
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Submit"
                    )
                }
            }


        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevTrade() {
    val navController = rememberNavController()
//    NewTrade()
    TradeIn(navController = navController, modifier = Modifier.fillMaxSize())
}


