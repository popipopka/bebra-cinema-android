package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.user.UserUpdateRequest
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.UpdateUserInputPort
import it.bebra.cinema.domain.port.out.UserOutputPort
import it.bebra.cinema.domain.service.request.manager.RequestManager
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class UpdateUserUseCase(
    private val userOutputPort: UserOutputPort,
    private val storage: EncryptedStorage,
    private val requestManager: RequestManager
) : UpdateUserInputPort {
    override suspend fun invoke(data: UserUpdateRequest): Resource<Unit> = requestManager {
        val token = storage.getString(StorageKeys.ACCESS_TOKEN)

        val result = userOutputPort.updateUser(token!!, data)

        handleErrorResult(javaClass.simpleName, result)

        result
    }
}