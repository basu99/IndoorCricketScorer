package com.example.indoorcricketscorer.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "matches",
    indices = [
        androidx.room.Index("date")
    ]
)
data class MatchEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "team_a")
    val teamA: String,

    @ColumnInfo(name = "team_b")
    val teamB: String,

    @ColumnInfo(name = "team_a_score")
    val teamAScore: Int,

    @ColumnInfo(name = "team_a_wickets")
    val teamAWickets: Int,

    @ColumnInfo(name = "team_b_score")
    val teamBScore: Int,

    @ColumnInfo(name = "team_b_wickets")
    val teamBWickets: Int,

    @ColumnInfo(name = "overs")
    val overs: Int,

    @ColumnInfo(name = "winner")
    val winner: String,

    @ColumnInfo(name = "date")
    val date: Long = System.currentTimeMillis()

)