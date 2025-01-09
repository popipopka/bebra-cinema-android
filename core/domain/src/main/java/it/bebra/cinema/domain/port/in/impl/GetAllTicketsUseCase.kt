package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.dto.ticket.TicketListResponse
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.GetAllTicketsInputPort
import it.bebra.cinema.domain.port.out.TicketOutputPort
import it.bebra.cinema.domain.service.request.manager.RequestManager
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class GetAllTicketsUseCase(
    private val ticketOutputPort: TicketOutputPort,
    private val storage: EncryptedStorage,
    private val requestManager: RequestManager
) : GetAllTicketsInputPort {
    override suspend fun invoke(
        lastId: Int?,
        limit: Int?
    ): Resource<PageResponse<TicketListResponse>> =
        requestManager {
            val token = storage.getString(StorageKeys.ACCESS_TOKEN)

            val result = ticketOutputPort.getAllTickets(token!!, lastId, limit)

            handleErrorResult(javaClass.simpleName, result)

            result
        }
}