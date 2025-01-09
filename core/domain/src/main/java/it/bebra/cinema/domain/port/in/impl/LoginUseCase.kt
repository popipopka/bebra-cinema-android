package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.auth.LoginRequest
import it.bebra.cinema.domain.dto.auth.LoginResponse
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.LoginInputPort
import it.bebra.cinema.domain.port.out.AuthOutputPort
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class LoginUseCase(
    private val authOutputPort: AuthOutputPort,
    private val storage: EncryptedStorage
) : LoginInputPort {

    override suspend fun invoke(data: LoginRequest): Resource<Unit> {
        val result = authOutputPort.login(data)

        handleSuccessResult(result, data)
        handleErrorResult(javaClass.simpleName, result)

        return Resource.Success(Unit)
    }

    private fun handleSuccessResult(result: Resource<LoginResponse>, data: LoginRequest) {
        if (result is Resource.Success) {
            storage.putString(StorageKeys.ACCESS_TOKEN, result.data.accessToken)
            storage.putString(StorageKeys.REFRESH_TOKEN, result.data.refreshToken)

            storage.putString(StorageKeys.USERNAME, data.username)
            storage.putString(StorageKeys.PASSWORD, data.password)
        }
    }
}