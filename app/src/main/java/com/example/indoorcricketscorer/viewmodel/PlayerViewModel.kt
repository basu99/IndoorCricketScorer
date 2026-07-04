package com.example.indoorcricketscorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.indoorcricketscorer.data.entity.PlayerEntity
import com.example.indoorcricketscorer.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PlayerViewModel(

    private val repository: PlayerRepository

) : ViewModel() {

    fun getAllPlayers(): Flow<List<PlayerEntity>> {

        return repository.getAllPlayers()

    }

    fun searchPlayers(query: String): Flow<List<PlayerEntity>> {

        return repository.searchPlayers(query)

    }



    fun savePlayer(name: String) {

        if (name.isBlank()) return

        viewModelScope.launch {

            repository.insertPlayer(name)

        }

    }

    fun deleteAllPlayers() {

        viewModelScope.launch {

            repository.deleteAllPlayers()

        }

    }

}