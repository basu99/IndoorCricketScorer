package com.example.indoorcricketscorer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.indoorcricketscorer.ui.screens.HomeScreen
import com.example.indoorcricketscorer.ui.screens.MatchHistoryScreen
import com.example.indoorcricketscorer.ui.screens.SettingsScreen
import com.example.indoorcricketscorer.ui.screens.StatisticsScreen
import com.example.indoorcricketscorer.ui.screens.NewMatchScreen
import com.example.indoorcricketscorer.ui.screens.LiveScoreScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.indoorcricketscorer.viewmodel.ScoreViewModel
import com.example.indoorcricketscorer.ui.screens.ScorecardScreen
object AppDestinations {
    const val HOME = "home"
    const val MATCH_HISTORY = "match_history"
    const val STATISTICS = "statistics"
    const val SETTINGS = "settings"
    const val NEW_MATCH = "new_match"
    const val LIVE_SCORE = "live_score"

    const val SCORECARD = "scorecard"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val scoreViewModel: ScoreViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME
    ) {
        composable(AppDestinations.HOME) {
            HomeScreen(
                onNewMatchClick = {
                    navController.navigate(AppDestinations.NEW_MATCH)
                },
                onStartMatch = {
                    // Will be implemented later
                },
                onMatchHistoryClick = {
                    navController.navigate(AppDestinations.MATCH_HISTORY)
                },
                onStatisticsClick = {
                    navController.navigate(AppDestinations.STATISTICS)
                },
                onSettingsClick = {
                    navController.navigate(AppDestinations.SETTINGS)
                }
            )
        }
        composable(AppDestinations.MATCH_HISTORY) {
            MatchHistoryScreen(
                vm = scoreViewModel
            )
        }
        composable(AppDestinations.STATISTICS) {
            StatisticsScreen()
        }
        composable(AppDestinations.SETTINGS) {
            SettingsScreen()
        }
        composable(AppDestinations.NEW_MATCH) {
            NewMatchScreen(
                vm = scoreViewModel,
                onStartMatch = {
                    navController.navigate(AppDestinations.LIVE_SCORE)
                }
            )
        }
        composable(AppDestinations.LIVE_SCORE) {
            LiveScoreScreen(
                vm = scoreViewModel,

                onHome = {
                    navController.navigate(AppDestinations.HOME)
                },

                onNewMatch = {
                    navController.navigate(AppDestinations.NEW_MATCH)
                },

                onScorecard = {
                    navController.navigate(AppDestinations.SCORECARD)
                }
            )
        }

        composable(AppDestinations.SCORECARD) {

            ScorecardScreen(
                vm = scoreViewModel
            )

        }
    }
}
