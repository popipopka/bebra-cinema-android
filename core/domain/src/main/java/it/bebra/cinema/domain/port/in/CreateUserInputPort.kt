package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.dto.user.UserCreateRequest
import it.bebra.cinema.domain.Resource

interface CreateUserInputPort {
    suspend fun invoke(user: UserCreateRequest): Resource<Unit>
}