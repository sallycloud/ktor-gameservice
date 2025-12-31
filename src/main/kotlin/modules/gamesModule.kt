package de.kr.modules

import de.kr.service.GamesService
import de.kr.service.GamesServiceImpl
import org.koin.dsl.module

val gamesModule = module {
    single<GamesService> { GamesServiceImpl }
}