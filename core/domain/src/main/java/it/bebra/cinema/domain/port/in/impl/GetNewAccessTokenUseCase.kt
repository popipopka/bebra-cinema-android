package it.bebra.cinema.domain.port.`in`.impl

import android.util.Log
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.auth.JwtAccessToken
import it.bebra.cinema.domain.dto.auth.JwtRefreshToken
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.GetNewAccessTokenInputPort
import it.bebra.cinema.domain.port.out.AuthOutputPort
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class GetNewAccessTokenUseCase(
    private val authOutputPort: AuthOutputPort,
    private val storage: EncryptedStorage
) : GetNewAccessTokenInputPort {
    override suspend fun invoke(): Boolean {
        val jwtRefreshToken = JwtRefreshToken(storage.getString(StorageKeys.REFRESH_TOKEN)!!)

        val result = authOutputPort.getNewAccessToken(jwtRefreshToken)

        handleSuccessResult(result)
        handleErrorResult(javaClass.simpleName, result)

        return result is Resource.Success
    }

    private fun handleSuccessResult(result: Resource<JwtAccessToken>) {
        if (result is Resource.Success) {
            storage.putString(StorageKeys.ACCESS_TOKEN, result.data.accessToken)
        }
    }
}