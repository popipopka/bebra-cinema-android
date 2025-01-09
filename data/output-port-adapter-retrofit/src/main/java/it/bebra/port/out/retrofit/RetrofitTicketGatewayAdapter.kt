package it.bebra.port.out.retrofit

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.dto.ticket.TicketCreateRequest
import it.bebra.cinema.domain.dto.ticket.TicketListResponse
import it.bebra.cinema.domain.port.out.TicketOutputPort
import it.bebra.cinema.remote.retrofit.api.gateway.TicketGateway
import it.bebra.port.out.retrofit.handler.ResponseHandler

class RetrofitTicketGatewayAdapter(
    private val retrofitTicketGateway: TicketGateway,
    private val handler: ResponseHandler
) : TicketOutputPort {

    override suspend fun getAllTickets(
        token: String,
        lastId: Int?,
        limit: Int?
    ): Resource<PageResponse<TicketListResponse>> =
        handler(retrofitTicketGateway.getAllTickets(token, lastId, limit))

    override suspend fun createTicket(
        token: String,
        ticket: TicketCreateRequest
    ): Resource<Unit> = handler(retrofitTicketGateway.createTicket(token, ticket))
}