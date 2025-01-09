package it.bebra.cinema.domain.service.request.chain

import it.bebra.cinema.domain.Resource

abstract class AbstractRequestHandler<T> : RequestHandler<T> {
    private var nextSuccess: RequestHandler<T>? = null
    private var nextFailure: RequestHandler<T>? = null

    override suspend fun setNextSuccess(handler: RequestHandler<T>): RequestHandler<T> {
        nextSuccess = handler
        return this
    }

    override suspend fun setNextFailure(handler: RequestHandler<T>): RequestHandler<T> {
        nextFailure = handler
        return this
    }

    protected suspend fun handleSuccess(): Resource<T> {
        return nextSuccess!!.handle()
    }

    protected suspend fun handleFailure(): Resource<T> {
        return nextFailure!!.handle()
    }
}