package it.bebra.cinema.remote.retrofit.api.gateway

import it.bebra.cinema.domain.dto.ticket.TicketCreateRequest
import it.bebra.cinema.remote.retrofit.api.TicketApi
import it.bebra.cinema.remote.retrofit.getJwtAuthHeader

class TicketGateway(
    private val ticketApi: TicketApi
) {

    suspend fun getAllTickets(accessToken: String, lastId: Int?, limit: Int?) =
        ticketApi.getAllTickets(getJwtAuthHeader(accessToken), lastId, limit)

    suspend fun createTicket(accessToken: String, ticket: TicketCreateRequest) =
        ticketApi.createTicket(getJwtAuthHeader(accessToken), ticket)
}