package de.kr

import de.kr.modules.gamesModule
import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.request.httpMethod
import io.ktor.server.request.uri
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.slf4j.event.Level

fun Application.configureFrameworks() {

    install(Koin) {
        slf4jLogger()
        modules(
            listOf(gamesModule)
        )
    }

    install(CallLogging) {
        level = Level.INFO

        format { call ->
            val method = call.request.httpMethod.value
            val uri = call.request.uri
            val status = call.response.status()?.value ?: "?"
            "[$status] $method $uri"
        }
    }
}
