package de.kr

import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.staticResources

fun Application.configureHTTP() {
    routing {
        swaggerUI(path = "swagger")
        staticResources(remotePath = "/openapi.yaml",
            basePackage = "openapi",
            index = "documentation.yaml")
        staticResources(
            remotePath = "/licenses",
            basePackage = "licenses",
            index = "licenses.json"
        )
    }

    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }
}
