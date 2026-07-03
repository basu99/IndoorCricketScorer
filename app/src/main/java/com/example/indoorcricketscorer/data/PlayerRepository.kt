package com.example.indoorcricketscorer.data

object PlayerRepository {

    private val players = mutableSetOf<String>()

    fun addPlayer(name: String) {

        if (name.isNotBlank()) {
            players.add(name.trim())
        }

    }

    fun addPlayers(names: List<String>) {

        names.forEach {
            addPlayer(it)
        }

    }

    fun search(query: String): List<String> {

        if (query.isBlank()) return emptyList()

        return players
            .filter {
                it.contains(query, ignoreCase = true)
            }
            .sorted()

    }

    fun getAllPlayers(): List<String> {

        return players.sorted()

    }

}