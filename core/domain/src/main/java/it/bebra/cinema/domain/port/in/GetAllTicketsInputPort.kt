package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.dto.ticket.TicketListResponse

interface GetAllTicketsInputPort {
    suspend fun invoke(lastId: Int?, limit: Int?): Resource<PageResponse<TicketListResponse>>
}