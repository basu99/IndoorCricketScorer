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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.AlertDialog
@Composable
fun MatchHistoryScreen(

    vm: ScoreViewModel,

    onMatchClick: (Long) -> Unit

) {

    val matches by vm.matchHistory.collectAsState(initial = emptyList())

    var showDeleteAllDialog by remember {

        mutableStateOf(false)

    }

    Button(

        enabled = matches.isNotEmpty(),

        onClick = {

            showDeleteAllDialog = true

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

                text = "🏏\n\nNo matches have been played yet.\nStart a new match to build your history.",

                style = MaterialTheme.typography.bodyLarge

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
                            "${match.teamA}  ${match.teamAScore}/${match.teamAWickets}"
                        )

                        Text(
                            "${match.teamB}  ${match.teamBScore}/${match.teamBWickets}"
                        )

                        Spacer(
                            Modifier.height(4.dp)
                        )

                        Text(
                            "${match.overs} Overs"
                        )

                        Spacer(

                            Modifier.height(4.dp)

                        )

                        Text(

                            text = "🏆 ${match.winner}",

                            style = MaterialTheme.typography.bodyMedium

                        )

                    }

                }

            }

        }

    }
    if (showDeleteAllDialog) {

        AlertDialog(

            onDismissRequest = {

                showDeleteAllDialog = false

            },

            title = {

                Text("Delete Match History")

            },

            text = {

                Text("This will permanently delete every saved match. Continue?")

            },

            confirmButton = {

                Button(

                    onClick = {

                        vm.deleteAllMatches()

                        showDeleteAllDialog = false

                    }

                ) {

                    Text("Delete")

                }

            },

            dismissButton = {

                Button(

                    onClick = {

                        showDeleteAllDialog = false

                    }

                ) {

                    Text("Cancel")

                }

            }

        )

    }

}