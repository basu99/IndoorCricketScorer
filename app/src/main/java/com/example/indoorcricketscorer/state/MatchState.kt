package com.example.indoorcricketscorer.state

data class MatchState(

    val teamA: String = "",

    val teamB: String = "",

    val totalOvers: Int = 0,

    val playersPerTeam: Int = 0,

    val innings: Int = 1,

    val target: Int = 0,

    val firstInningsScore: Int = 0,

    val firstInningsWickets: Int = 0,

    val recentBalls: List<String> = emptyList(),

    // Team Scores
    val runs: Int = 0,

    val wickets: Int = 0,

    val balls: Int = 0,

    // Team Players
    val teamAPlayers: List<Player> = emptyList(),

    val teamBPlayers: List<Player> = emptyList(),

// Current Match Players
    val strikerIndex: Int = 0,

    val nonStrikerIndex: Int = 1,

    val bowlerIndex: Int = 0,

    val currentOverBalls: Int = 0

)