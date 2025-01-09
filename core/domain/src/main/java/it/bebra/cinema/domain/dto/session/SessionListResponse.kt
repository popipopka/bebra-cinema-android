package it.bebra.cinema.domain.dto.session

data class SessionListResponse(
    val id: Int,
    val hallName: String,
    val startTime: String,
    val price: Float
)