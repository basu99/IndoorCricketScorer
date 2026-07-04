package com.example.indoorcricketscorer.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScoreButton(

    text: String,

    enabled: Boolean = true,

    onClick: () -> Unit

) {

    Button(

        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),

        enabled = enabled,

        onClick = onClick

    ) {

        Text(

            text,

            style = MaterialTheme.typography.titleLarge

        )

    }

}