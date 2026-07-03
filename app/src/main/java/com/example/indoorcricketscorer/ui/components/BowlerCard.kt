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
fun BowlerCard(

    bowler: Player?

) {

    Card {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                "Bowling",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(12.dp))

            bowler?.let {

                Text(it.name)

                Text(
                    "${it.wickets}/${it.runsConceded}"
                )

            }

        }

    }

}