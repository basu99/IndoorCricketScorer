package com.example.indoorcricketscorer.state

data class Player(

    val name: String,

    // Batting
    val runs: Int = 0,
    val balls: Int = 0,
    val fours: Int = 0,
    val fives: Int = 0,
    val sixes: Int = 0,
    val isOut: Boolean = false,

    // Bowling
    val wickets: Int = 0,
    val overs: Int = 0,
    val ballsBowled: Int = 0,
    val runsConceded: Int = 0,

    // Match Status
    val hasBatted: Boolean = false,
    val hasBowled: Boolean = false

) {

    val strikeRate: Double
        get() =
            if (balls == 0)
                0.0
            else
                runs.toDouble() * 100 / balls

    val economy: Double
        get() =
            if (ballsBowled == 0)
                0.0
            else
                runsConceded.toDouble() * 6 / ballsBowled

}