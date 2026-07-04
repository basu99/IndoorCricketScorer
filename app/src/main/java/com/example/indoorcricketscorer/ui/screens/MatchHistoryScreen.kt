package com.example.indoorcricketscorer.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.indoorcricketscorer.viewmodel.ScoreViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button

@Composable
fun MatchHistoryScreen(

    vm: ScoreViewModel,

    onMatchClick: (Long) -> Unit

) {

    val matches by vm.matchHistory.collectAsState(initial = emptyList())

    Button(

        onClick = {

            vm.deleteAllMatches()

        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {

        Text("Delete All History")

    }

    Spacer(Modifier.height(8.dp))

    if (matches.isEmpty()) {

        Box(

            modifier = Modifier.fillMaxSize(),

            contentAlignment = Alignment.Center

        ) {

            Text(

                "No matches played yet."

            )

        }

    } else {

        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            contentPadding = PaddingValues(16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            items(matches) { match ->

                Card(

                    modifier = Modifier.clickable {

                        onMatchClick(match.id)

                    }

                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                "${match.teamA} vs ${match.teamB}",
                                style = MaterialTheme.typography.titleMedium
                            )

                            IconButton(
                                onClick = {

                                    vm.deleteMatch(match.id)

                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Match"
                                )

                            }

                        }

                        Spacer(Modifier.height(8.dp))

                        Text(
                            "${match.teamAScore}/${match.teamAWickets} vs ${match.teamBScore}/${match.teamBWickets}"
                        )

                        Text(
                            "Winner: ${match.winner}"
                        )

                    }

                }

            }

        }

    }

}