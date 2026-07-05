package com.example.indoorcricketscorer.state

data class LiveMatchUiState(

    val score: Int = 0,

    val wickets: Int = 0,

    val overs: Float = 0f,

    val target: Int? = null,

    val currentRunRate: Float = 0f,

    val requiredRunRate: Float? = null,

    val striker: Player? = null,

    val nonStriker: Player? = null,

    val bowler: Player? = null,

    val recentBalls: List<String> = emptyList(),

    val isMatchCompleted: Boolean = false,

    val canUndo: Boolean = false

)