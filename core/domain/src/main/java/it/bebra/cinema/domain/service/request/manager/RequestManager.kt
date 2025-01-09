package it.bebra.cinema.domain.service.request.manager

import it.bebra.cinema.domain.Resource

interface RequestManager {
    suspend operator fun <T> invoke(operation: suspend () -> Resource<T>): Resource<T>
}