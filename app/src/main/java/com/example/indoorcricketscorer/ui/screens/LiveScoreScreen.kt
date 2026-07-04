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

@Composable
fun LiveScoreScreen(
    vm: ScoreViewModel,
    onHome: () -> Unit,
    onNewMatch: () -> Unit,
    onScorecard: () -> Unit,
    onMatchFinished: () -> Unit
) {


    val state = vm.state
    LaunchedEffect(vm.isMatchFinished) {

        if (vm.isMatchFinished) {

            onMatchFinished()

        }

    }
    val battingPlayers = vm.battingTeamPlayers
    val currentRunRate =
        if (state.balls == 0)
            0.0
        else
            (state.runs.toDouble() * 6) / state.balls

    val completedOvers = "${state.balls / 6}.${state.balls % 6}"

    val requiredRuns =
        if (state.innings == 2)
            (state.target - state.runs).coerceAtLeast(0)
        else
            0

    val ballsRemaining =
        (state.totalOvers * 6) - state.balls

    val requiredRunRate =
        if (ballsRemaining > 0)
            requiredRuns.toDouble() * 6 / ballsRemaining
        else
            0.0


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
                text = "Overs : $completedOvers / ${state.totalOvers}"
            )

            Text(
                text = "Current Run Rate : %.2f".format(currentRunRate)
            )

            if (state.innings == 2) {

                Text(
                    text = "Target : ${state.target}"
                )

                Text(
                    text = "Need $requiredRuns from $ballsRemaining balls"
                )

                Text(
                    text = "Required RR : %.2f".format(requiredRunRate)
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

                val economy =
                    if (it.ballsBowled == 0)
                        0.0
                    else
                        (it.runsConceded.toDouble() * 6) / it.ballsBowled

                Text(
                    text =
                        "${it.name}  ${it.wickets}-${it.runsConceded} (${it.ballsBowled / 6}.${it.ballsBowled % 6})  Econ %.2f"
                            .format(economy)
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

                val runsNeeded = state.target - state.runs

                val ballsLeft = (state.totalOvers * 6) - state.balls

                Text(
                    text = "Need $runsNeeded runs from $ballsLeft balls"
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

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onHome
            ) {

                Text("Home")

            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    vm.resetMatch()

                    onNewMatch()

                }

            ) {

                Text("New Match")

            }


            Button(
                enabled =
                    !vm.isMatchFinished &&
                            !vm.isInningsFinished &&
                            !vm.waitingForNextBatter,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.addRuns(0) }
            ) {
                Text("Dot Ball")
            }

            Button(
                enabled =
                    !vm.isMatchFinished &&
                            !vm.isInningsFinished &&
                            !vm.waitingForNextBatter,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.addRuns(1) }
            ) {
                Text("1 Run")
            }

            Button(
                enabled =
                    !vm.isMatchFinished &&
                            !vm.isInningsFinished &&
                            !vm.waitingForNextBatter,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.addRuns(2) }
            ) {
                Text("2 Runs")
            }

            Button(
                enabled =
                    !vm.isMatchFinished &&
                            !vm.isInningsFinished &&
                            !vm.waitingForNextBatter,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.addRuns(3) }
            ) {
                Text("3 Runs")
            }

            Button(
                enabled =
                    !vm.isMatchFinished &&
                            !vm.isInningsFinished &&
                            !vm.waitingForNextBatter,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.addRuns(4) }
            ) {
                Text("4 Runs")
            }

            Button(
                enabled =
                    !vm.isMatchFinished &&
                            !vm.isInningsFinished &&
                            !vm.waitingForNextBatter,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.addRuns(5) }
            ) {
                Text("5 Runs")
            }

            Button(
                enabled =
                    !vm.isMatchFinished &&
                            !vm.isInningsFinished &&
                            !vm.waitingForNextBatter,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.addRuns(6) }
            ) {
                Text("6 Runs")
            }

            Button(
                enabled =
                    !vm.isMatchFinished &&
                            !vm.isInningsFinished &&
                            !vm.waitingForNextBatter,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.wicket() }
            ) {
                Text("Wicket")
            }

            Button(
                enabled = !vm.isMatchFinished,
                onClick = { vm.undo() }
            ) {
                Text("Undo")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onScorecard
            ) {

                Text("Scorecard")

            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    vm.resetMatch()

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
}