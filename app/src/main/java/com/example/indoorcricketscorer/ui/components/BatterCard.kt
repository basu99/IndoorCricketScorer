package com.example.indoorcricketscorer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.indoorcricketscorer.state.Player

@Composable
fun BatterCard(

    striker: Player?,

    nonStriker: Player?

) {

    Card {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                "Batting",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(12.dp))

            striker?.let {

                Text(
                    "🏏 ${it.name}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    "${it.runs} (${it.balls})"
                )

                Text(
                    "SR %.1f".format(it.strikeRate),
                    style = MaterialTheme.typography.bodySmall
                )

            }

            Spacer(Modifier.height(12.dp))

            nonStriker?.let {

                Text(
                    it.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    "${it.runs} (${it.balls})"
                )

                Text(
                    "SR %.1f".format(it.strikeRate),
                    style = MaterialTheme.typography.bodySmall
                )

            }

        }

    }

}