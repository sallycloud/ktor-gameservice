package de.kr.model

import kotlinx.serialization.Serializable

@Serializable
data class GameDTO(val id: Long,
                   val name: String,
                   val playerMin: Int?,
                   val playersMax: Int?,
                   val fromAge: Int?,
                   val isCompetitive: Boolean?)