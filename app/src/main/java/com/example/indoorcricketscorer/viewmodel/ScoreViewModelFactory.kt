package com.example.indoorcricketscorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.indoorcricketscorer.repository.MatchRepository

class ScoreViewModelFactory(
    private val repository: MatchRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}