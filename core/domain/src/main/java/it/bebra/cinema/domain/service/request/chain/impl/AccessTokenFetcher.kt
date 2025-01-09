package it.bebra.cinema.domain.service.request.chain.impl

import android.util.Log
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.port.`in`.GetNewAccessTokenInputPort
import it.bebra.cinema.domain.service.request.chain.AbstractRequestHandler
import it.bebra.cinema.domain.service.request.chain.RequestHandler.Companion.LOG_TAG

class AccessTokenFetcher<T>(
    private val getNewAccessToken: GetNewAccessTokenInputPort
) : AbstractRequestHandler<T>() {

    override suspend fun handle(): Resource<T> {
        Log.d(LOG_TAG, "Попытка обновления Access токена")
        val result = getNewAccessToken.invoke()

        return if (result) {
            Log.d(LOG_TAG, "Access токен успешно обновлен")
            handleSuccess()

        } else {
            Log.d(LOG_TAG, "Ошибка обновления Access токена")
            handleFailure()
        }
    }
}