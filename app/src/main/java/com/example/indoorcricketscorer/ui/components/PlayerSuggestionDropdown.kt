package com.example.indoorcricketscorer.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import com.example.indoorcricketscorer.data.entity.PlayerEntity

@Composable
fun PlayerSuggestionDropdown(

    players: List<PlayerEntity>,

    onPlayerSelected: (PlayerEntity) -> Unit

) {

    if (players.isEmpty()) return

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        LazyColumn {

            items(players) { player ->

                Text(

                    text = player.name,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            onPlayerSelected(player)

                        }
                        .padding(16.dp),

                    style = MaterialTheme.typography.bodyLarge

                )

            }

        }

    }

}