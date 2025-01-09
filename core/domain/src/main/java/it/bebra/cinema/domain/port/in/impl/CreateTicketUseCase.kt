package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.ticket.TicketCreateRequest
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.CreateTicketInputPort
import it.bebra.cinema.domain.port.out.TicketOutputPort
import it.bebra.cinema.domain.service.request.manager.RequestManager
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class CreateTicketUseCase(
    private val ticketOutputPort: TicketOutputPort,
    private val storage: EncryptedStorage,
    private val requestManager: RequestManager
) : CreateTicketInputPort {
    override suspend fun invoke(ticket: TicketCreateRequest): Resource<Unit> = requestManager {
        val token = storage.getString(StorageKeys.ACCESS_TOKEN)

        val result = ticketOutputPort.createTicket(token!!, ticket)

        handleErrorResult(javaClass.simpleName, result)

        result
    }


}