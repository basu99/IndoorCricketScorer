package com.example.indoorcricketscorer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MatchActionBar(

    onUndo: () -> Unit,

    onScorecard: () -> Unit,

    onHome: () -> Unit,

    onNewMatch: () -> Unit

) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {

        Box(Modifier.weight(1f)) {

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = onUndo

            ) {

                Text("Undo")

            }

        }

        Box(Modifier.weight(1f)) {

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = onScorecard

            ) {

                Text("Card")

            }

        }

        Box(Modifier.weight(1f)) {

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = onHome

            ) {

                Text("Home")

            }

        }

        Box(Modifier.weight(1f)) {

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = onNewMatch

            ) {

                Text("New")

            }

        }

    }

}