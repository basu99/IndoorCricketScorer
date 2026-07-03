package com.example.indoorcricketscorer.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PlayerChip(
    playerName: String,
    onRemove: () -> Unit
) {

    InputChip(
        selected = false,
        onClick = onRemove,
        label = {
            Text(
                text = playerName,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove"
            )
        }
    )
}