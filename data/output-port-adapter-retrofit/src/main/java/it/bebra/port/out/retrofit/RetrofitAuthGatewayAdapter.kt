package it.bebra.port.out.retrofit

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.auth.JwtAccessToken
import it.bebra.cinema.domain.dto.auth.JwtRefreshToken
import it.bebra.cinema.domain.dto.auth.LoginRequest
import it.bebra.cinema.domain.dto.auth.LoginResponse
import it.bebra.cinema.domain.port.out.AuthOutputPort
import it.bebra.cinema.remote.retrofit.api.gateway.AuthGateway
import it.bebra.port.out.retrofit.handler.ResponseHandler

class RetrofitAuthGatewayAdapter(
    private val retrofitAuthGateway: AuthGateway,
    private val handler: ResponseHandler
) : AuthOutputPort {
    override suspend fun login(login: LoginRequest): Resource<LoginResponse> =
        handler(retrofitAuthGateway.login(login))

    override suspend fun getNewAccessToken(token: JwtRefreshToken): Resource<JwtAccessToken> =
        handler (retrofitAuthGateway.getNewAccessToken(token))
}