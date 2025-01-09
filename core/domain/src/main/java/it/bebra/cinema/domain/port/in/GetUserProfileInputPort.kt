package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.user.UserDetailResponse

interface GetUserProfileInputPort {
    suspend fun invoke(): Resource<UserDetailResponse>
}