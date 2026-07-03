package com.example.indoorcricketscorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.indoorcricketscorer.viewmodel.ScoreViewModel

@Composable
fun ScorecardScreen(
    vm: ScoreViewModel
) {

    val state = vm.state

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {

            Text(
                "${state.teamA} vs ${state.teamB}",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                "${state.runs}/${state.wickets}"
            )

            Divider()

            Text(
                "Batting",
                style = MaterialTheme.typography.titleLarge
            )

        }

        items(vm.battingTeamPlayers) { batter ->

            Text(
                "${batter.name}"
            )

            Text(
                "${batter.runs} (${batter.balls})   4s:${batter.fours}   5s:${batter.fives}   6s:${batter.sixes}   SR ${"%.1f".format(batter.strikeRate)}"
            )

            Divider()

        }

        item {

            Spacer(Modifier.height(16.dp))

            Text(
                "Bowling",
                style = MaterialTheme.typography.titleLarge
            )

        }

        items(vm.bowlingTeamPlayers) { bowler ->

            Text(
                bowler.name
            )

            Text(
                "${bowler.wickets}/${bowler.runsConceded}   Overs ${bowler.ballsBowled / 6}.${bowler.ballsBowled % 6}   Econ ${"%.2f".format(bowler.economy)}"
            )

            Divider()

        }

    }

}