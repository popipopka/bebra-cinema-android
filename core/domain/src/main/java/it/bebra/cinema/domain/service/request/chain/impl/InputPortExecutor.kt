package it.bebra.cinema.domain.service.request.chain.impl

import android.util.Log
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.Resource.Empty
import it.bebra.cinema.domain.Resource.Success
import it.bebra.cinema.domain.service.request.chain.AbstractRequestHandler
import it.bebra.cinema.domain.service.request.chain.RequestHandler
import it.bebra.cinema.domain.service.request.chain.RequestHandler.Companion.LOG_TAG

class InputPortExecutor<T>(
    private val operation: suspend () -> Resource<T>
) : AbstractRequestHandler<T>() {

    override suspend fun handle(): Resource<T> {
        Log.d(LOG_TAG, "Вызов метода input порта")

        return when (val result = operation.invoke()) {
            is Success,
            is Empty -> {
                Log.d(LOG_TAG, "Вызов метода завершился успешно")
                result
            }

            else -> {
                Log.d(LOG_TAG, "Вызов метода завершился с ошибкой")
                super.handleFailure()
            }
        }
    }

    override suspend fun setNextSuccess(handler: RequestHandler<T>): RequestHandler<T> {
        throw IllegalStateException()
    }
}