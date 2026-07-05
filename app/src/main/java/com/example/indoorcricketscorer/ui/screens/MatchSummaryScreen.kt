package com.example.indoorcricketscorer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.indoorcricketscorer.viewmodel.ScoreViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

@Composable
fun MatchSummaryScreen(
    vm: ScoreViewModel,
    onHome: () -> Unit,
    onNewMatch: () -> Unit,
    onScorecard: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            "Match Finished",
            style = MaterialTheme.typography.headlineMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "🏆 ${vm.winner}",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = vm.resultText,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    Modifier.height(12.dp)
                )

                Text(
                    "Final Score",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    "${vm.state.teamA}: ${vm.state.firstInningsScore}/${vm.state.firstInningsWickets}"
                )

                Text(
                    "${vm.state.teamB}: ${vm.state.runs}/${vm.state.wickets}"
                )

            }

        }

        Button(
            onClick = onScorecard,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Scorecard")
        }

        Button(
            onClick = onNewMatch,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("New Match")
        }

        Button(
            onClick = onHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Home")
        }

    }

}