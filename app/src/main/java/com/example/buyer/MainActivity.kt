package com.example.buyer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.buyer.ui.theme.BuyerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyerTheme {
                Surface {
//                    Navigate()
                }
            }
            Navigate()

        }
    }
}
