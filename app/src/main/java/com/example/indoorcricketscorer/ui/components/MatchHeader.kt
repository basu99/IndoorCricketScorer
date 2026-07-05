package com.example.indoorcricketscorer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MatchHeader(
    teamA: String,
    teamB: String,
    overs: Int,
    players: Int
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 4.dp
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "$teamA vs $teamB",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "$overs Overs • $players Players",
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}