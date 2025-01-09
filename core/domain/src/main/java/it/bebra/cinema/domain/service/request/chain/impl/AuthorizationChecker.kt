package it.bebra.cinema.domain.service.request.chain.impl

import android.util.Log
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.port.`in`.IsUserLoggedInInputPort
import it.bebra.cinema.domain.service.request.chain.AbstractRequestHandler
import it.bebra.cinema.domain.service.request.chain.RequestHandler
import it.bebra.cinema.domain.service.request.chain.RequestHandler.Companion.LOG_TAG

class AuthorizationChecker<T>(
    private val isUserLoggedInInputPort: IsUserLoggedInInputPort
) : AbstractRequestHandler<T>() {

    override suspend fun handle(): Resource<T> {
        Log.d(LOG_TAG, "Проверка авторизации пользователя")
        val isLogged = isUserLoggedInInputPort.invoke()

        return if (isLogged) {
            Log.d(LOG_TAG, "Пользователь авторизирован")
            super.handleSuccess()
        } else {
            Log.d(LOG_TAG, "Пользователь не авторизирован")
            Resource.Unauthorized
        }
    }

    override suspend fun setNextFailure(handler: RequestHandler<T>): RequestHandler<T> {
        throw IllegalStateException()
    }
}