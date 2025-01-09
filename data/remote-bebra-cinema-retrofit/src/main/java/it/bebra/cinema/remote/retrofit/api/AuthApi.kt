package it.bebra.cinema.remote.retrofit.api

import it.bebra.cinema.domain.dto.auth.JwtAccessToken
import it.bebra.cinema.domain.dto.auth.JwtRefreshToken
import it.bebra.cinema.domain.dto.auth.LoginRequest
import it.bebra.cinema.domain.dto.auth.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/login")
    suspend fun login(
        @Body body: LoginRequest,
    ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/token")
    suspend fun getNewAccessToken(
        @Body body: JwtRefreshToken,
    ): Response<JwtAccessToken>
}