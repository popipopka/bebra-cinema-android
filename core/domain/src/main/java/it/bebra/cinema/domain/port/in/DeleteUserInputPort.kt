package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.Resource

interface DeleteUserInputPort {
    suspend fun invoke() : Resource<Unit>
}