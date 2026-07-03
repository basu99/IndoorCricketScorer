package com.example.indoorcricketscorer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TeamPlayerSection(
    title: String,
    input: String,
    players: List<String>,
    maxPlayers: Int,
    onInputChange: (String) -> Unit,
    onAdd: () -> Unit,
    onRemove: (String) -> Unit
) {

    Column {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = input,
            onValueChange = onInputChange,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Enter Player Name")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onAdd,
            modifier = Modifier.fillMaxWidth(),
            enabled = input.isNotBlank() && players.size < maxPlayers
        ) {
            Text(
                "Add Player (${players.size}/$maxPlayers)"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            players.forEach { player ->

                PlayerChip(
                    playerName = player,
                    onRemove = {
                        onRemove(player)
                    }
                )

            }

        }

    }

}