package it.bebra.cinema.domain.port.out

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.auth.JwtAccessToken
import it.bebra.cinema.domain.dto.auth.JwtRefreshToken
import it.bebra.cinema.domain.dto.auth.LoginRequest
import it.bebra.cinema.domain.dto.auth.LoginResponse

interface AuthOutputPort {
    suspend fun login(login: LoginRequest): Resource<LoginResponse>

    suspend fun getNewAccessToken(token: JwtRefreshToken): Resource<JwtAccessToken>
}