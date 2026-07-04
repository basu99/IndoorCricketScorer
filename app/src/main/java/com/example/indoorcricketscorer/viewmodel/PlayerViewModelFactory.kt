package com.example.indoorcricketscorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.indoorcricketscorer.repository.PlayerRepository

class PlayerViewModelFactory(

    private val repository: PlayerRepository

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(

        modelClass: Class<T>

    ): T {

        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {

            return PlayerViewModel(repository) as T

        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }

}