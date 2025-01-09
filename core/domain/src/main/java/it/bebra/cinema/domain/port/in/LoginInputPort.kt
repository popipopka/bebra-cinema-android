package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.auth.LoginRequest

interface LoginInputPort {
    suspend fun invoke(login: LoginRequest): Resource<Unit>
}