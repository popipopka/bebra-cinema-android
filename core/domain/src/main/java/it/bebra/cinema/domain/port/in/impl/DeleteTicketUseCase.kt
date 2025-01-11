package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.DeleteTicketInputPort
import it.bebra.cinema.domain.port.out.TicketOutputPort
import it.bebra.cinema.domain.service.request.manager.RequestManager
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class DeleteTicketUseCase(
    private val ticketOutputPort: TicketOutputPort,
    private val storage: EncryptedStorage,
    private val requestManager: RequestManager
) : DeleteTicketInputPort {
    override suspend fun invoke(id: Int): Resource<Unit> = requestManager {
        val token = storage.getString(StorageKeys.ACCESS_TOKEN)

        val result = ticketOutputPort.deleteTicket(token!!, id)

        handleErrorResult(javaClass.simpleName, result)

        result
    }
}