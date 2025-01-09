package it.bebra.cinema.remote.retrofit.api.gateway

import it.bebra.cinema.domain.dto.auth.JwtRefreshToken
import it.bebra.cinema.domain.dto.auth.LoginRequest
import it.bebra.cinema.remote.retrofit.api.AuthApi

class AuthGateway(
    private val authApi: AuthApi
) {

    suspend fun login(login: LoginRequest) =
        authApi.login(login)

    suspend fun getNewAccessToken(token: JwtRefreshToken) =
        authApi.getNewAccessToken(token)
}