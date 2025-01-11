package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.DeleteUserInputPort
import it.bebra.cinema.domain.port.out.UserOutputPort
import it.bebra.cinema.domain.service.request.manager.RequestManager
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class DeleteUserUseCase(
    private val userOutputPort: UserOutputPort,
    private val storage: EncryptedStorage,
    private val requestManager: RequestManager
) : DeleteUserInputPort {
    override suspend fun invoke(): Resource<Unit> = requestManager {
        val token = storage.getString(StorageKeys.ACCESS_TOKEN)

        val result = userOutputPort.deleteUser(token!!)

        handleErrorResult(javaClass.simpleName, result)

        result
    }
}