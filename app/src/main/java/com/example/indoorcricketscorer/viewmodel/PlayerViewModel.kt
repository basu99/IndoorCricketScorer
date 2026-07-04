package com.example.indoorcricketscorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.indoorcricketscorer.data.entity.PlayerEntity
import com.example.indoorcricketscorer.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

class PlayerViewModel(

    private val repository: PlayerRepository

) : ViewModel() {

    private val _suggestions =
        MutableStateFlow<List<PlayerEntity>>(emptyList())

    val suggestions: StateFlow<List<PlayerEntity>>
        get() = _suggestions

    fun search(query: String) {

        viewModelScope.launch {

            repository.searchPlayers(query).collect {

                _suggestions.value = it

            }

        }

    }

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