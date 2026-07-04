package com.example.indoorcricketscorer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScoringKeypad(

    enabled: Boolean,

    onDot: () -> Unit,

    onOne: () -> Unit,

    onTwo: () -> Unit,

    onThree: () -> Unit,

    onFour: () -> Unit,

    onFive: () -> Unit,

    onSix: () -> Unit,

    onWicket: () -> Unit

) {

    Column {

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
                ScoreButton("0", enabled, onDot)
            }

            androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
                ScoreButton("1", enabled, onOne)
            }

            androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
                ScoreButton("2", enabled, onTwo)
            }

            androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
                ScoreButton("3", enabled, onThree)
            }

        }

        Spacer(Modifier.height(8.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
                ScoreButton("4", enabled, onFour)
            }

            androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
                ScoreButton("5", enabled, onFive)
            }

            androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
                ScoreButton("6", enabled, onSix)
            }

            androidx.compose.foundation.layout.Box(Modifier.weight(1f)) {
                ScoreButton("W", enabled, onWicket)
            }

        }

    }

}