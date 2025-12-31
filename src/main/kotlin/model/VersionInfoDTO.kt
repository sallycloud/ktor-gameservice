package de.kr.model

import kotlinx.serialization.Serializable

@Serializable
data class VersionInfoDTO(val app: String = "ktor-gameservice",
                   val version: String)