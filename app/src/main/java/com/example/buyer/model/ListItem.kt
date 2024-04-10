package com.example.buyer.model

import androidx.compose.ui.graphics.vector.ImageVector


data class ListItem(
    val title: String,
    val onClick: () -> Unit,
    val icon: ImageVector
)
