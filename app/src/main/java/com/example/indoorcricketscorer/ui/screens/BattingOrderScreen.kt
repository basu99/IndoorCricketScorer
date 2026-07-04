package com.example.indoorcricketscorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.indoorcricketscorer.viewmodel.ScoreViewModel

@Composable
fun BattingOrderScreen(

    vm: ScoreViewModel,

    onContinue: () -> Unit

) {

    val players = vm.battingTeamPlayers

    val bowlers = vm.bowlingTeamPlayers

    var striker by remember {

        mutableStateOf<Int?>(null)

    }

    var nonStriker by remember {

        mutableStateOf<Int?>(null)

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {


        Spacer(modifier = Modifier.height(24.dp))



        LazyColumn {

            items(players.indices.toList()) { index ->

                val player = players[index]

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween

                ) {

                    Text(player.name)

                    Row {

                        RadioButton(

                            selected = striker == index,

                            enabled = nonStriker != index,

                            onClick = {

                                striker = index

                            }

                        )

                        RadioButton(

                            selected = nonStriker == index,

                            enabled = striker != index,

                            onClick = {

                                nonStriker = index

                            }

                        )

                    }

                }

            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Opening Batters",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            vm.state.teamA,
            style = MaterialTheme.typography.titleMedium
        )

        var openingBowler by remember {

            mutableStateOf<Int?>(null)

        }

        Spacer(modifier = Modifier.height(24.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Selected Players",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            "Striker : ${
                striker?.let { players[it].name } ?: "-"
            }"
        )

        Text(
            "Non-Striker : ${
                nonStriker?.let { players[it].name } ?: "-"
            }"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(

            enabled =
                striker != null &&
                        nonStriker != null &&
                        striker != nonStriker,

            modifier = Modifier.fillMaxWidth(),

            onClick = {

                vm.setOpeningBatters(
                    striker!!,
                    nonStriker!!
                )

                onContinue()

            }

        ) {

            Text("Start Match")
        }

    }

}