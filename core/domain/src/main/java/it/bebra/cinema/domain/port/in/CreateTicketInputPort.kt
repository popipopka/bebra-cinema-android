package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.ticket.TicketCreateRequest

interface CreateTicketInputPort {
    suspend fun invoke(ticket: TicketCreateRequest): Resource<Unit>
}