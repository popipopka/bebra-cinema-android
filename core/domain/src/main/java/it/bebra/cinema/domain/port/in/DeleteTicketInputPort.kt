package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.Resource

interface DeleteTicketInputPort {
    suspend fun invoke(id: Int) : Resource<Unit>
}