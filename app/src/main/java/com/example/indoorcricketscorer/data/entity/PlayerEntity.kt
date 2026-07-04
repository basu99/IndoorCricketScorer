package com.example.indoorcricketscorer.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(

    tableName = "players",

    indices = [

        Index(

            value = ["name"],

            unique = true

        )

    ]

)
data class PlayerEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String

)