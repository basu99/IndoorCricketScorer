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
import com.example.indoorcricketscorer.viewmodel.ScoreViewModelFactory
import com.example.indoorcricketscorer.ui.screens.MatchDetailsScreen
import com.example.indoorcricketscorer.ui.screens.MatchSummaryScreen
import com.example.indoorcricketscorer.repository.MatchRepository
import com.example.indoorcricketscorer.repository.PlayerRepository
import com.example.indoorcricketscorer.ui.screens.BattingOrderScreen
import com.example.indoorcricketscorer.viewmodel.PlayerViewModel
import com.example.indoorcricketscorer.viewmodel.PlayerViewModelFactory

object AppDestinations {
    const val HOME = "home"
    const val MATCH_HISTORY = "match_history"
    const val STATISTICS = "statistics"
    const val SETTINGS = "settings"
    const val NEW_MATCH = "new_match"
    const val LIVE_SCORE = "live_score"

    const val MATCH_DETAILS = "match_details/{matchId}"

    fun matchDetailsRoute(matchId: Long): String {

        return "match_details/$matchId"

    }

    const val SCORECARD = "scorecard"

    const val MATCH_SUMMARY = "match_summary"

    const val BATTING_ORDER = "batting_order"
}

@Composable
fun AppNavigation(

    matchRepository: MatchRepository,

    playerRepository: PlayerRepository

) {
    val navController = rememberNavController()

    val scoreViewModel: ScoreViewModel = viewModel(

        factory = ScoreViewModelFactory(matchRepository)

    )

    val playerViewModel: PlayerViewModel = viewModel(

        factory = PlayerViewModelFactory(playerRepository)

    )

    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME
    ) {
        composable(AppDestinations.HOME) {
            HomeScreen(
                onNewMatchClick = {
                    navController.navigate(AppDestinations.NEW_MATCH) {
                        launchSingleTop = true
                    }
                },
                onStartMatch = {
                    navController.navigate(AppDestinations.BATTING_ORDER) {
                        launchSingleTop = true
                    }
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

                vm = scoreViewModel,

                onMatchClick = { id ->

                    navController.navigate(

                        AppDestinations.matchDetailsRoute(id)

                    )

                }

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

                playerVm = playerViewModel,
                onStartMatch = {
                    navController.navigate(AppDestinations.BATTING_ORDER)
                }
            )
        }
        composable(AppDestinations.LIVE_SCORE) {
            LiveScoreScreen(
                vm = scoreViewModel,

                onHome = {

                    navController.navigate(AppDestinations.HOME) {

                        popUpTo(AppDestinations.HOME) {

                            inclusive = false

                        }

                        launchSingleTop = true

                    }

                },

                onNewMatch = {

                    scoreViewModel.resetMatch()

                    navController.navigate(AppDestinations.NEW_MATCH) {

                        popUpTo(AppDestinations.HOME)

                        launchSingleTop = true

                    }

                },

                onScorecard = {
                    navController.navigate(AppDestinations.SCORECARD) {
                        launchSingleTop = true
                    }
                },

                onMatchFinished = {
                    navController.navigate(AppDestinations.MATCH_SUMMARY) {

                        popUpTo(AppDestinations.LIVE_SCORE) {

                            inclusive = true

                        }

                    }
                }
            )
        }

        composable(

            route = AppDestinations.MATCH_DETAILS

        ) { backStackEntry ->

            val id =

                backStackEntry.arguments
                    ?.getString("matchId")
                    ?.toLongOrNull()

            if (id != null) {

                scoreViewModel.loadMatch(id)

                scoreViewModel.selectedMatch?.let {

                    MatchDetailsScreen(

                        match = it

                    )

                }

            }

        }

        composable(AppDestinations.SCORECARD) {

            ScorecardScreen(
                vm = scoreViewModel
            )

        }

        composable(AppDestinations.MATCH_SUMMARY) {

            MatchSummaryScreen(

                vm = scoreViewModel,

                onHome = {

                    scoreViewModel.resetMatch()

                    navController.navigate(AppDestinations.HOME) {

                        popUpTo(AppDestinations.HOME)

                        launchSingleTop = true

                    }

                },

                onNewMatch = {

                    scoreViewModel.resetMatch()

                    navController.navigate(AppDestinations.NEW_MATCH) {

                        popUpTo(AppDestinations.HOME)

                        launchSingleTop = true

                    }

                },

                onScorecard = {

                    navController.navigate(AppDestinations.SCORECARD) {

                        launchSingleTop = true

                    }

                }

            )

        }

        composable(AppDestinations.BATTING_ORDER) {

            BattingOrderScreen(

                vm = scoreViewModel,

                onContinue = {

                    navController.navigate(AppDestinations.LIVE_SCORE) {

                        launchSingleTop = true

                    }

                }

            )

        }
    }
}
