package it.bebra.cinema.domain.service.request.chain.impl

import android.util.Log
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.auth.LoginRequest
import it.bebra.cinema.domain.port.`in`.LoginInputPort
import it.bebra.cinema.domain.service.request.chain.AbstractRequestHandler
import it.bebra.cinema.domain.service.request.chain.RequestHandler
import it.bebra.cinema.domain.service.request.chain.RequestHandler.Companion.LOG_TAG
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class RefreshTokenFetcher<T>(
    private val loginInputPort: LoginInputPort,
    private val storage: EncryptedStorage
) : AbstractRequestHandler<T>() {

    override suspend fun handle(): Resource<T> {
        Log.d(LOG_TAG, "Обновление Refresh токена")

        val username = storage.getString(StorageKeys.USERNAME)
        val password = storage.getString(StorageKeys.PASSWORD)

        return when (val result = loginInputPort.invoke(LoginRequest(username!!, password!!))) {
            is Resource.Success -> {
                Log.d(LOG_TAG, "Refresh токен успешно обновлен")
                super.handleSuccess()
            }

            is Resource.Error -> {
                Log.d(LOG_TAG, "Ошибка обновление Refresh токена")
                result
            }

            else -> {
                Log.d(LOG_TAG, "Ошибка обновление Refresh токена: Пользователь не авторизирован")
                Resource.Unauthorized
            }
        }
    }

    override suspend fun setNextFailure(handler: RequestHandler<T>): RequestHandler<T> {
        throw IllegalStateException()
    }
}