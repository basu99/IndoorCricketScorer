package com.example.indoorcricketscorer.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.indoorcricketscorer.state.MatchState
import com.example.indoorcricketscorer.state.Player
import androidx.compose.runtime.mutableStateListOf
import com.example.indoorcricketscorer.repository.MatchRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.indoorcricketscorer.data.entity.MatchEntity

class ScoreViewModel(
    private val repository: MatchRepository
) : ViewModel() {

    val isInningsFinished: Boolean
        get() =
            state.balls >= state.totalOvers * 6 ||
                    state.wickets >= state.playersPerTeam - 1

    val isMatchFinished: Boolean
        get() =

            state.innings == 2 &&
                    state.balls >= state.totalOvers * 6

    val winner: String

        get() {

            if (!isMatchFinished)
                return ""

            return if (state.runs >= state.target)

                state.teamB

            else

                state.teamA


        }

    private fun saveMatchIfFinished() {

        if (!isMatchFinished || matchSaved) return

        viewModelScope.launch {

            repository.insertMatch(

                teamA = state.teamA,

                teamB = state.teamB,

                teamAScore = state.firstInningsScore,

                teamAWickets = 0,

                teamBScore = state.runs,

                teamBWickets = state.wickets,

                overs = state.totalOvers,

                winner = winner

            )

            matchSaved = true
        }


    }




    val resultText: String

        get() {

            if (!isMatchFinished)
                return ""

            return if (state.runs >= state.target) {

                "${state.teamB} won by ${state.playersPerTeam - state.wickets - 1} wickets"

            } else {

                "${state.teamA} won by ${state.target - state.runs - 1} runs"

            }

        }

    var state by mutableStateOf(MatchState())
        private set

    var waitingForNextBatter by mutableStateOf(false)
        private set

    var dismissedBatterIndex by mutableStateOf(-1)
        private set
    private var matchSaved = false

    var selectedMatch by mutableStateOf<MatchEntity?>(null)
        private set

    private val history = mutableListOf<MatchState>()

    val battingTeamPlayers: List<Player>
        get() =
            if (state.innings == 1)
                state.teamAPlayers
            else
                state.teamBPlayers

    val matchHistory = repository.getAllMatches()

    val bowlingTeamPlayers: List<Player>
        get() =
            if (state.innings == 1)
                state.teamBPlayers
            else
                state.teamAPlayers

    val recentBalls: List<String>
        get() = state.recentBalls

    val striker: Player?
        get() =
            battingTeamPlayers.getOrNull(state.strikerIndex)

    val nonStriker: Player?
        get() =
            battingTeamPlayers.getOrNull(state.nonStrikerIndex)

    val currentBowler: Player?
        get() =
            bowlingTeamPlayers.getOrNull(state.bowlerIndex)

    val winningMargin: String

        get() {

            if (!isMatchFinished)
                return ""

            return if (state.runs >= state.target) {

                "${state.playersPerTeam - state.wickets - 1} wickets"

            } else {

                "${state.target - state.runs - 1} runs"

            }

        }

    val bestBatter: Player?

        get() =
            battingTeamPlayers.maxByOrNull { it.runs }

    val bestBowler: Player?

        get() =
            bowlingTeamPlayers.maxByOrNull { it.wickets }

    fun createMatch(

        teamA: String,

        teamB: String,

        overs: Int,

        players: Int,

        teamAPlayerNames: List<String>,

        teamBPlayerNames: List<String>




    ) {


        val teamAPlayers = teamAPlayerNames.map {

            Player(name = it.trim())

        }

        val teamBPlayers = teamBPlayerNames.map {

            Player(name = it.trim())

        }

        state = MatchState(

            teamA = teamA,

            teamB = teamB,

            totalOvers = overs,

            playersPerTeam = players,

            teamAPlayers = teamAPlayers,

            teamBPlayers = teamBPlayers,

            strikerIndex = 0,

            nonStrikerIndex =
                if (teamAPlayers.size > 1) 1 else 0,

            bowlerIndex = 0,

            currentOverBalls = 0



        )
        matchSaved = false
        history.clear()
    }

    fun addRuns(run: Int) {

        history.add(state)

        if (state.balls >= state.totalOvers * 6) {
            return
        }

        val newBalls = state.balls + 1
        val overBalls = state.currentOverBalls + 1
        val updatedRuns = state.runs + run

        // ---------- Update striker ----------

        val players = battingTeamPlayers.toMutableList()

        if (state.strikerIndex == -1)
            return

        val striker = players[state.strikerIndex]

        players[state.strikerIndex] = striker.copy(

            runs = striker.runs + run,

            balls = striker.balls + 1,

            fours = striker.fours + if (run == 4) 1 else 0,

            fives = striker.fives + if (run == 5) 1 else 0,

            sixes = striker.sixes + if (run == 6) 1 else 0,

            hasBatted = true

        )

        updateBattingPlayers(players)

        val bowlers = bowlingTeamPlayers.toMutableList()

        val bowler = bowlers[state.bowlerIndex]

        bowlers[state.bowlerIndex] =
            bowler.copy(

                ballsBowled = bowler.ballsBowled + 1,

                runsConceded = bowler.runsConceded + run

            )

        updateBowlingPlayers(bowlers)

        // ---------- Update match ----------

        state = state.copy(

            runs = updatedRuns,

            balls = newBalls,

            currentOverBalls =
                if (overBalls == 6) 0 else overBalls,

            recentBalls =
                (state.recentBalls + run.toString())
                    .takeLast(12)

        )

        if (run % 2 == 1) {

            swapStrike()

        }

        if (overBalls == 6) {

            endOver()

        }

        if (

            state.innings == 2 &&
            newBalls >= state.totalOvers * 6 &&
            updatedRuns < state.target

        ) {


            saveMatchIfFinished()      // <-- ADD THIS

        }

        if (

            state.innings == 2 &&
            updatedRuns >= state.target

        ) {

            state = state.copy(
                balls = state.totalOvers * 6,
                currentOverBalls = 0
            )


            saveMatchIfFinished()

            history.clear()

        }

    }

    fun wicket() {

        history.add(state)

        if (state.balls >= state.totalOvers * 6)
            return

        val players = battingTeamPlayers.toMutableList()

        if (state.strikerIndex == -1)
            return

        val striker = players[state.strikerIndex]

        players[state.strikerIndex] =
            striker.copy(
                isOut = true,
                balls = striker.balls + 1,
                hasBatted = true
            )

        updateBattingPlayers(players)

        val bowlers = bowlingTeamPlayers.toMutableList()

        val bowler = bowlers[state.bowlerIndex]

        bowlers[state.bowlerIndex] =
            bowler.copy(

                wickets = bowler.wickets + 1,

                ballsBowled = bowler.ballsBowled + 1

            )

        updateBowlingPlayers(bowlers)


        state = state.copy(

            wickets = state.wickets + 1,

            balls = state.balls + 1,

            currentOverBalls =
                if (state.currentOverBalls == 5) 0
                else state.currentOverBalls + 1,

            recentBalls =
                (state.recentBalls + "W").takeLast(12)
        )

        dismissedBatterIndex = state.strikerIndex

        val remainingBatters = players.count {

            !it.isOut

        }

        waitingForNextBatter = remainingBatters > 1

        if (remainingBatters <= 1) {

            waitingForNextBatter = false

            if (state.innings == 2) {

                state = state.copy(
                    balls = state.totalOvers * 6
                )

                saveMatchIfFinished()

            }

        }

        if (state.currentOverBalls == 0) {

            endOver()

        }

    }

    fun undo() {

        if (history.isNotEmpty()) {
            state = history.removeAt(history.lastIndex)
        }
    }

    fun setOpeningBatters(

        strikerIndex: Int,

        nonStrikerIndex: Int

    ) {

        state = state.copy(

            strikerIndex = strikerIndex,

            nonStrikerIndex = nonStrikerIndex

        )

    }

    fun resetMatch() {

        state = MatchState()

        history.clear()

        waitingForNextBatter = false

        dismissedBatterIndex = -1

        matchSaved = false

    }



    fun startSecondInnings() {

        history.clear()

        state = state.copy(

            recentBalls = emptyList(),

            innings = 2,

            firstInningsScore = state.runs,

            target = state.runs + 1,

            runs = 0,

            wickets = 0,

            balls = 0,

            strikerIndex = 0,

            nonStrikerIndex =
                if (state.teamBPlayers.size > 1) 1 else 0,

            bowlerIndex = 0,

            currentOverBalls = 0

        )
        waitingForNextBatter = false
        dismissedBatterIndex = -1
        matchSaved = false
    }

    private fun updateBattingPlayers(
        players: List<Player>
    ) {

        if (state.innings == 1) {

            state = state.copy(
                teamAPlayers = players
            )

        } else {

            state = state.copy(
                teamBPlayers = players
            )

        }

    }

    private fun updateBowlingPlayers(
        players: List<Player>
    ) {

        if (state.innings == 1) {

            state = state.copy(
                teamBPlayers = players
            )

        } else {

            state = state.copy(
                teamAPlayers = players
            )

        }

    }

    fun selectNextBatter(index: Int) {

        val player = battingTeamPlayers[index]

        if (player.isOut) return

        state = state.copy(
            strikerIndex = index
        )

        waitingForNextBatter = false

        dismissedBatterIndex = -1

        val remainingBatters = battingTeamPlayers.count {

            !it.isOut

        }

        if (remainingBatters <= 1) {

            waitingForNextBatter = false

        }

    }

    fun deleteMatch(

        id: Long

    ) {

        viewModelScope.launch {

            repository.deleteMatch(id)

        }

    }

    fun deleteAllMatches() {

        viewModelScope.launch {

            repository.deleteAllMatches()

        }

    }

    private fun swapStrike() {

        state = state.copy(

            strikerIndex = state.nonStrikerIndex,

            nonStrikerIndex = state.strikerIndex

        )

    }

    fun loadMatch(

        id: Long

    ) {

        viewModelScope.launch {

            selectedMatch = repository.getMatchById(id)

        }

    }

    private fun endOver() {

        swapStrike()

        val nextBowler =

            if (state.bowlerIndex + 1 >= bowlingTeamPlayers.size)
                0
            else
                state.bowlerIndex + 1

        state = state.copy(

            currentOverBalls = 0,

            bowlerIndex = nextBowler

        )



    }


}

