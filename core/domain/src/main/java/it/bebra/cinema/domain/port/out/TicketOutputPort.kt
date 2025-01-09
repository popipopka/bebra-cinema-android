package it.bebra.cinema.domain.port.out

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.dto.ticket.TicketCreateRequest
import it.bebra.cinema.domain.dto.ticket.TicketListResponse

interface TicketOutputPort {

    suspend fun getAllTickets(
        token: String,
        lastId: Int?,
        limit: Int?
    ): Resource<PageResponse<TicketListResponse>>

    suspend fun createTicket(token: String, ticket: TicketCreateRequest): Resource<Unit>
}