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
                text = "${state.teamA} vs ${state.teamB}",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "${state.totalOvers} Overs • ${state.playersPerTeam} Players",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "${state.runs}/${state.wickets}",
                style = MaterialTheme.typography.displaySmall
            )

            HorizontalDivider()

            Text(
                text = "Batting",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                "Batting",
                style = MaterialTheme.typography.titleLarge
            )

        }

        items(vm.battingTeamPlayers) { batter ->

            Text(
                text = batter.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${batter.runs} (${batter.balls})",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "4s ${batter.fours} • 5s ${batter.fives} • 6s ${batter.sixes}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Strike Rate ${"%.1f".format(batter.strikeRate)}",
                style = MaterialTheme.typography.bodySmall
            )

            HorizontalDivider()

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
                text = bowler.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${bowler.wickets}/${bowler.runsConceded}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Overs ${bowler.ballsBowled / 6}.${bowler.ballsBowled % 6}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Economy ${"%.2f".format(bowler.economy)}",
                style = MaterialTheme.typography.bodySmall
            )

            HorizontalDivider()

        }

    }

}