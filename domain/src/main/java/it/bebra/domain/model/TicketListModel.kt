package it.bebra.domain.model

import java.time.LocalDateTime

data class TicketListModel (
    val id: Int,
    val filmTitle: String,
    val posterUrl: String?,
    val date: LocalDateTime,
    val place: Int,
    val row: Int
)