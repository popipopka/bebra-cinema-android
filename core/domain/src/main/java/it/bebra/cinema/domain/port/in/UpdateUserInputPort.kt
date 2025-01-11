package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.user.UserUpdateRequest

interface UpdateUserInputPort {
    suspend fun invoke(data: UserUpdateRequest): Resource<Unit>
}