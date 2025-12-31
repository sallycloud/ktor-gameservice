package de.kr

import de.kr.model.VersionInfoDTO
import de.kr.service.GamesService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.getKoin

fun Application.configureRouting() {

    val version = environment.config
        .propertyOrNull("ktor.application.version")
        ?.getString()
        ?: "dev"
    val gamesService = getKoin().get<GamesService>()

    routing {
        get("/games") {
            val name = call.queryParameters["name"]
            if (name==null) {
                call.respond(gamesService.getAllGames())
            } else {
                call.respond(gamesService.getGames(name))
            }
        }
        get("/") {
            call.respond(VersionInfoDTO(version = version))
        }
    }
}
