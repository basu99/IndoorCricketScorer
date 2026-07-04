package com.example.indoorcricketscorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.indoorcricketscorer.data.database.AppDatabase
import com.example.indoorcricketscorer.navigation.AppNavigation
import com.example.indoorcricketscorer.repository.MatchRepository
import com.example.indoorcricketscorer.repository.PlayerRepository
import com.example.indoorcricketscorer.ui.theme.IndoorCricketScorerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val database = AppDatabase.getInstance(applicationContext)

        val matchRepository = MatchRepository(
            database.matchDao()
        )

        val playerRepository = PlayerRepository(
            database.playerDao()
        )

        setContent {

            IndoorCricketScorerTheme {

                AppNavigation(

                    matchRepository = matchRepository,

                    playerRepository = playerRepository

                )

            }

        }

    }

}