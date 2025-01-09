package it.bebra.cinema.domain.service.request.chain

import it.bebra.cinema.domain.Resource

interface RequestHandler<T> {
    companion object {
        val LOG_TAG: String = RequestHandler::class.java.simpleName
    }

    suspend fun setNextSuccess(handler: RequestHandler<T>): RequestHandler<T>

    suspend fun setNextFailure(handler: RequestHandler<T>): RequestHandler<T>

    suspend fun handle(): Resource<T>
}