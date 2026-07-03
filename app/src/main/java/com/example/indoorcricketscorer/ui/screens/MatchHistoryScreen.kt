package com.example.indoorcricketscorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.indoorcricketscorer.viewmodel.ScoreViewModel

@Composable
fun MatchHistoryScreen(

    vm: ScoreViewModel

) {

    LazyColumn(

        modifier = Modifier.fillMaxSize(),

        contentPadding = PaddingValues(16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        items(vm.matchHistory) { match ->

            Card {

                Column(

                    modifier = Modifier.padding(16.dp)

                ) {

                    Text(

                        "${match.teamA} vs ${match.teamB}",

                        style = MaterialTheme.typography.titleMedium

                    )

                    Text(

                        "Score : ${match.runs}/${match.wickets}"

                    )

                    Text(

                        "Overs : ${match.totalOvers}"

                    )

                }

            }

        }

    }

}