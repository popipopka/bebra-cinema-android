package it.bebra.cinema.remote.retrofit.api

import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.dto.ticket.TicketCreateRequest
import it.bebra.cinema.domain.dto.ticket.TicketListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TicketApi {

    @GET("/api/v1/tickets")
    suspend fun getAllTickets(
        @Header("Authorization") jwtHeader: String,
        @Query("lastId") lastId: Int?,
        @Query("limit") limit: Int?,
    ): Response<PageResponse<TicketListResponse>>

    @Headers("Content-Type: application/json")
    @POST("api/v1/tickets")
    suspend fun createTicket(
        @Header("Authorization") jwtHeader: String,
        @Body body: TicketCreateRequest,
    ): Response<Unit>
}