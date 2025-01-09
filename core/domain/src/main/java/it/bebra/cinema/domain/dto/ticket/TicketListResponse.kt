package it.bebra.cinema.domain.dto.ticket

data class TicketListResponse(
    val id: Int,
    val row: Int,
    val place: Int,
    val price: Int,
    val sessionStartTime: String,
    val movieName: String,
    val moviePosterUrl: String? = null
)