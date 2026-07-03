package com.example.indoorcricketscorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.indoorcricketscorer.navigation.AppNavigation
import com.example.indoorcricketscorer.ui.theme.IndoorCricketScorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IndoorCricketScorerTheme {
                AppNavigation()
            }
        }
    }
}
