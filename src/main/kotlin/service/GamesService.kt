package de.kr.service

import de.kr.model.GameDTO

interface GamesService {
    fun getAllGames(): List<GameDTO>
    fun getGames(name: String): List<GameDTO>
}

object GamesServiceImpl: GamesService {

    val store: Map<Int, GameDTO>
        get() = mapOf(
            1 to GameDTO(1, "Tic Tac Toe", 2, 2, 6, true),
            2 to GameDTO(2, "Chess", 2, 2, 6, true),
            3 to GameDTO(3, "Pandemic", 2, 4, 8, false)
        )

    override fun getAllGames(): List<GameDTO> {
        return store.values.toList()
    }

    override fun getGames(name: String): List<GameDTO> {
        return store.entries.asSequence()
            .map{ it.value }
            .filter{
                it.name.contains(name)
            }
            .toList()
    }
}
