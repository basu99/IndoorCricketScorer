package com.example.indoorcricketscorer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.indoorcricketscorer.viewmodel.ScoreViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.indoorcricketscorer.ui.components.RecentBallsRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import com.example.indoorcricketscorer.ui.components.BatterCard
import com.example.indoorcricketscorer.ui.components.BowlerCard
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import com.example.indoorcricketscorer.ui.components.ScoringKeypad
import com.example.indoorcricketscorer.ui.components.MatchActionBar

@Composable
fun LiveScoreScreen(
    vm: ScoreViewModel,
    onHome: () -> Unit,
    onNewMatch: () -> Unit,
    onScorecard: () -> Unit,
    onMatchFinished: () -> Unit
) {


    val state = vm.state

    var showResetDialog by remember {

        mutableStateOf(false)

    }

    var showHomeDialog by remember {

        mutableStateOf(false)

    }

    var showNewMatchDialog by remember {

        mutableStateOf(false)

    }

    LaunchedEffect(vm.isMatchFinished) {

        if (vm.isMatchFinished) {

            onMatchFinished()

        }

    }
    val battingPlayers = vm.battingTeamPlayers



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "${state.teamA} vs ${state.teamB}",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "${state.totalOvers} Overs • ${state.playersPerTeam} Players",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "${state.runs}/${state.wickets}",
                style = MaterialTheme.typography.displaySmall
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Box(
                    modifier = Modifier.weight(1f)
                ) {

                    BatterCard(
                        striker = vm.striker,
                        nonStriker = vm.nonStriker
                    )

                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {

                    BowlerCard(
                        bowler = vm.currentBowler
                    )

                }

            }

            Text(
                text = "Overs : ${vm.completedOvers} / ${state.totalOvers}"
            )

            Text(
                text = "Current Run Rate : %.2f".format(vm.currentRunRate)
            )

            if (state.innings == 2) {

                Text(
                    text = "Target : ${state.target}"
                )

                Text(
                    text = "Need ${vm.requiredRuns} from ${vm.ballsRemaining} balls"
                )

                Text(
                    text = "Required RR : %.2f".format(vm.requiredRunRate)
                )

            }

            Text(
                text = "Batting",
                style = MaterialTheme.typography.titleLarge
            )

            vm.striker?.let {

                Text(
                    text = "🏏 ${it.name}  ${it.runs} (${it.balls}) *"
                )

            }

            vm.nonStriker?.let {

                Text(
                    text = "${it.name}  ${it.runs} (${it.balls})"
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Bowling",
                style = MaterialTheme.typography.titleLarge
            )

            vm.currentBowler?.let {

                Text(
                    text =
                        "${it.name}  ${it.wickets}-${it.runsConceded} (${it.ballsBowled / 6}.${it.ballsBowled % 6})  Econ %.2f"
                            .format(it.economy)
                )

            }



            Text(
                text = "Recent Balls",
                style = MaterialTheme.typography.titleMedium
            )

            RecentBallsRow(
                balls = vm.recentBalls
            )

            if (state.innings == 2 && !vm.isMatchFinished) {

                Text(
                    text = "Need ${vm.requiredRuns} runs from ${vm.ballsRemaining} balls"
                )

            }

            Text(
                text = if (state.wickets == 0)
                    "No wickets lost"
                else
                    "${state.wickets} wicket(s) lost"
            )


            if (vm.isMatchFinished) {

                Text(
                    text = "🏆 Winner",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = vm.winner,
                    style = MaterialTheme.typography.displaySmall
                )

            } else if (vm.isInningsFinished && state.innings == 1) {

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        vm.startSecondInnings()
                    }
                ) {
                    Text("Start Chase (${state.teamB})")
                }

            }

            if (vm.waitingForNextBatter) {

                Text(
                    text = "Select Next Batter",
                    style = MaterialTheme.typography.titleLarge
                )

                vm.battingTeamPlayers.forEachIndexed { index, player ->

                    if (
                        !player.isOut &&
                        index != state.nonStrikerIndex &&
                        index != state.strikerIndex
                    ) {

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                vm.selectNextBatter(index)
                            }
                        ) {

                            Text(player.name)

                        }

                    }

                }

            }

            if (vm.waitingForNextBowler) {

                Text(
                    text = "Select Next Bowler",
                    style = MaterialTheme.typography.titleLarge
                )

                vm.bowlingTeamPlayers.forEachIndexed { index, player ->

                    Button(
                        enabled = index != vm.previousBowlerIndex,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            vm.selectNextBowler(index)
                        }
                    ) {
                        Text(player.name)
                    }

                }

            }

            MatchActionBar(

                onUndo = {

                    vm.undo()

                },

                onScorecard = onScorecard,

                onHome = {

                    showHomeDialog = true

                },

                onNewMatch = {

                    showNewMatchDialog = true

                }

            )


            ScoringKeypad(

                enabled =
                    !vm.isMatchFinished &&
                            !vm.isInningsFinished &&
                            !vm.waitingForNextBatter &&
                            !vm.waitingForNextBowler,

                onDot = {

                    vm.addRuns(0)

                },

                onOne = {

                    vm.addRuns(1)

                },

                onTwo = {

                    vm.addRuns(2)

                },

                onThree = {

                    vm.addRuns(3)

                },

                onFour = {

                    vm.addRuns(4)

                },

                onFive = {

                    vm.addRuns(5)

                },

                onSix = {

                    vm.addRuns(6)

                },

                onWicket = {

                    vm.wicket()

                },

                onWide = {

                    vm.wide()

                },

                onNoBall = {
                    vm.noBall()
                },

            )

            Button(
                enabled =
                    !vm.isMatchFinished &&
                            !vm.isInningsFinished &&
                            !vm.waitingForNextBatter &&
                            !vm.waitingForNextBowler,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.wicket() }
            ) {
                Text("Wicket")
            }



            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    showResetDialog = true

                }
            ) {

                Text("Reset Match")

            }

        }
    }
    if (vm.waitingForNextBatter) {

        AlertDialog(

            onDismissRequest = {},

            title = {

                Text("Select Next Batter")

            },

            text = {

                Column {

                    battingPlayers.forEachIndexed { index, player ->

                        val canBat =

                            !player.isOut &&
                                    !player.hasBatted &&
                                    index != state.nonStrikerIndex

                        Button(

                            enabled = canBat,

                            modifier = Modifier.fillMaxWidth(),

                            onClick = {

                                vm.selectNextBatter(index)

                            }

                        ) {

                            Text(player.name)

                        }

                    }

                }

            },

            confirmButton = {}



        )


    }
    if (showResetDialog) {

        AlertDialog(

            onDismissRequest = {

                showResetDialog = false

            },

            title = {

                Text("Reset Match")

            },

            text = {

                Text(

                    "Are you sure you want to reset this match? All current progress will be lost."

                )

            },

            confirmButton = {

                Button(

                    onClick = {

                        vm.resetMatch()

                        showResetDialog = false

                    }

                ) {

                    Text("Yes")

                }

            },

            dismissButton = {

                Button(

                    onClick = {

                        showResetDialog = false

                    }

                ) {

                    Text("Cancel")

                }

            }

        )

    }
    if (showHomeDialog) {

        AlertDialog(

            onDismissRequest = {

                showHomeDialog = false

            },

            title = {

                Text("Leave Match")

            },

            text = {

                Text("Leave this match and return to Home?")

            },

            confirmButton = {

                Button(

                    onClick = {

                        showHomeDialog = false

                        onHome()

                    }

                ) {

                    Text("Yes")

                }

            },

            dismissButton = {

                Button(

                    onClick = {

                        showHomeDialog = false

                    }

                ) {

                    Text("Cancel")

                }

            }

        )

    }
    if (showNewMatchDialog) {

        AlertDialog(

            onDismissRequest = {

                showNewMatchDialog = false

            },

            title = {

                Text("Start New Match")

            },

            text = {

                Text("Current match progress will be lost. Continue?")

            },

            confirmButton = {

                Button(

                    onClick = {

                        vm.resetMatch()

                        showNewMatchDialog = false

                        onNewMatch()

                    }

                ) {

                    Text("Yes")

                }

            },

            dismissButton = {

                Button(

                    onClick = {

                        showNewMatchDialog = false

                    }

                ) {

                    Text("Cancel")

                }

            }

        )

    }

}