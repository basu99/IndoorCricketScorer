package com.example.indoorcricketscorer.repository

import com.example.indoorcricketscorer.data.dao.MatchDao
import com.example.indoorcricketscorer.data.entity.MatchEntity
import kotlinx.coroutines.flow.Flow

class MatchRepository(
    private val matchDao: MatchDao
) {

    fun getAllMatches(): Flow<List<MatchEntity>> =
        matchDao.getAllMatches()

    suspend fun insertMatch(

        teamA: String,

        teamB: String,

        teamAScore: Int,

        teamAWickets: Int,

        teamBScore: Int,

        teamBWickets: Int,

        overs: Int,

        winner: String

    ): Long {

        return matchDao.insertMatch(

            MatchEntity(

                teamA = teamA,

                teamB = teamB,

                teamAScore = teamAScore,

                teamAWickets = teamAWickets,

                teamBScore = teamBScore,

                teamBWickets = teamBWickets,

                overs = overs,

                winner = winner

            )

        )

    }

}