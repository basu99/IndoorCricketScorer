package com.example.indoorcricketscorer.repository

import com.example.indoorcricketscorer.data.dao.PlayerDao
import com.example.indoorcricketscorer.data.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

class PlayerRepository(

    private val playerDao: PlayerDao

) {

    fun getAllPlayers(): Flow<List<PlayerEntity>> {

        return playerDao.getAllPlayers()

    }

    fun searchPlayers(query: String): Flow<List<PlayerEntity>> {

        return playerDao.searchPlayers(query)

    }

    suspend fun insertPlayer(name: String) {

        playerDao.insertPlayer(

            PlayerEntity(

                name = name.trim()

            )

        )

    }

    suspend fun deleteAllPlayers() {

        playerDao.deleteAllPlayers()

    }

}