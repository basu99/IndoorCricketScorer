package com.example.indoorcricketscorer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.indoorcricketscorer.data.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Query("SELECT * FROM players ORDER BY name ASC")
    fun getAllPlayers(): Flow<List<PlayerEntity>>

    @Query("SELECT * FROM players WHERE name LIKE :query || '%' ORDER BY name ASC")
    fun searchPlayers(query: String): Flow<List<PlayerEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlayer(player: PlayerEntity)

    @Query("DELETE FROM players")
    suspend fun deleteAllPlayers()

    @Query(
        "SELECT * FROM players WHERE LOWER(name) = LOWER(:name) LIMIT 1"
    )
    suspend fun getPlayerByName(
        name: String
    ): PlayerEntity?

}