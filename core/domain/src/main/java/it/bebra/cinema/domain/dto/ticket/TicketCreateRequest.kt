package it.bebra.cinema.domain.dto.ticket

data class TicketCreateRequest(
    val sessionId: Int,
    val row: Int,
    val place: Int,
)