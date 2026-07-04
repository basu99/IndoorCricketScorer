package com.example.indoorcricketscorer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.indoorcricketscorer.viewmodel.ScoreViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.indoorcricketscorer.ui.components.TeamPlayerSection
import com.example.indoorcricketscorer.viewmodel.PlayerViewModel
import com.example.indoorcricketscorer.ui.components.PlayerSuggestionDropdown
import androidx.compose.runtime.collectAsState

@Composable
fun NewMatchScreen(

    vm: ScoreViewModel,

    playerVm: PlayerViewModel,

    onStartMatch: () -> Unit

) {

    var teamA by remember { mutableStateOf("") }
    var teamB by remember { mutableStateOf("") }
    var overs by remember { mutableStateOf("") }
    var players by remember { mutableStateOf("") }

    var teamAPlayerInput by remember { mutableStateOf("") }

    var teamBPlayerInput by remember { mutableStateOf("") }

    var teamAPlayers by remember {
        mutableStateOf(listOf<String>())
    }

    var teamBPlayers by remember {
        mutableStateOf(listOf<String>())
    }

    val oversValue = overs.toIntOrNull() ?: 0

    val playersValue = players.toIntOrNull() ?: 0

    val canStartMatch =
        teamA.isNotBlank() &&
                teamB.isNotBlank() &&
                !teamA.equals(teamB, ignoreCase = true) &&
                oversValue > 0 &&
                playersValue > 0 &&
                teamAPlayers.size == playersValue &&
                teamBPlayers.size == playersValue

    val teamASuggestions by playerVm
        .searchPlayers(teamAPlayerInput)
        .collectAsState(initial = emptyList())

    val teamBSuggestions by playerVm
        .searchPlayers(teamBPlayerInput)
        .collectAsState(initial = emptyList())

    val suggestions by playerVm
        .suggestions
        .collectAsState()


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
                text = "Create New Match",
                style = MaterialTheme.typography.headlineMedium
            )

            OutlinedTextField(
                value = teamA,
                onValueChange = { teamA = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Team A") }
            )

            OutlinedTextField(
                value = teamB,
                onValueChange = { teamB = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Team B") }
            )

            OutlinedTextField(
                value = overs,
                onValueChange = { overs = it.filter { c -> c.isDigit() } },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Overs") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                value = players,
                onValueChange = {

                    players = it.filter { c -> c.isDigit() }

                    val max = players.toIntOrNull()

                    if (max != null) {

                        if (teamAPlayers.size > max) {
                            teamAPlayers = teamAPlayers.take(max)
                        }

                        if (teamBPlayers.size > max) {
                            teamBPlayers = teamBPlayers.take(max)
                        }

                    }

                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Players") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )

            )

            TeamPlayerSection(

                title = "Team A Players",

                input = teamAPlayerInput,

                players = teamAPlayers,

                maxPlayers = players.toIntOrNull() ?: 0,

                onInputChange = {

                    teamAPlayerInput = it

                    playerVm.search(it)

                },

                onAdd = {

                    val name = teamAPlayerInput.trim()

                    val max = players.toIntOrNull() ?: 0

                    if (
                        name.isNotBlank() &&
                        teamAPlayers.size < max &&
                        teamAPlayers.none { it.equals(name, true) }
                    ) {

                        teamAPlayers = teamAPlayers + name

                        teamAPlayerInput = ""

                    }

                },

                onRemove = {

                    teamAPlayers = teamAPlayers.filter { player ->

                        player != it

                    }

                }

            )
            PlayerSuggestionDropdown(

                players = teamASuggestions,

                onPlayerSelected = { player ->

                    teamAPlayerInput = player.name

                }

            )

            TeamPlayerSection(

                title = "Team B Players",

                input = teamBPlayerInput,

                players = teamBPlayers,

                maxPlayers = players.toIntOrNull() ?: 0,

                onInputChange = {

                    teamBPlayerInput = it

                    playerVm.search(it)

                },

                onAdd = {

                    val name = teamBPlayerInput.trim()

                    val max = players.toIntOrNull() ?: 0

                    if (
                        name.isNotBlank() &&
                        teamBPlayers.size < max &&
                        teamBPlayers.none { it.equals(name, true) }
                    ) {

                        teamBPlayers = teamBPlayers + name

                        teamBPlayerInput = ""

                    }

                },

                onRemove = {

                    teamBPlayers = teamBPlayers.filter { player ->

                        player != it

                    }

                }

            )
            PlayerSuggestionDropdown(

                players = teamBSuggestions,

                onPlayerSelected = { player ->

                    teamBPlayerInput = player.name

                }

            )

            Button(

                enabled = canStartMatch,

                onClick = {

                    teamAPlayers.forEach {

                        playerVm.savePlayer(it)

                    }

                    teamBPlayers.forEach {

                        playerVm.savePlayer(it)

                    }

                    vm.createMatch(


                        teamA = teamA,

                        teamB = teamB,

                        overs = overs.toIntOrNull() ?: 0,

                        players = players.toIntOrNull() ?: 0,

                        teamAPlayerNames = teamAPlayers,

                        teamBPlayerNames = teamBPlayers


                    )

                    onStartMatch()

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Match")
            }
        }
    }
}