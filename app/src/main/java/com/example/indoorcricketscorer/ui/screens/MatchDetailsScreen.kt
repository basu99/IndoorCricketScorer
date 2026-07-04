package com.example.indoorcricketscorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.indoorcricketscorer.data.entity.MatchEntity

@Composable
fun MatchDetailsScreen(

    match: MatchEntity

) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        Text(
            "${match.teamA} vs ${match.teamB}",
            style = MaterialTheme.typography.headlineMedium
        )

        HorizontalDivider()

        Text(
            "MATCH RESULT",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            match.winner,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(12.dp))

        Card {

            Column(
                Modifier.padding(16.dp)
            ) {

                Text(
                    match.teamA,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    "${match.teamAScore}/${match.teamAWickets}"
                )

            }

        }

        Card {

            Column(
                Modifier.padding(16.dp)
            ) {

                Text(
                    match.teamB,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    "${match.teamBScore}/${match.teamBWickets}"
                )

            }

        }

        HorizontalDivider()

        Text(
            "Overs : ${match.overs}"
        )

    }

}