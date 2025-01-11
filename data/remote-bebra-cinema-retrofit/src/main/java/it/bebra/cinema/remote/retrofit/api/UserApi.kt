package it.bebra.cinema.remote.retrofit.api

import it.bebra.cinema.domain.dto.user.UserCreateRequest
import it.bebra.cinema.domain.dto.user.UserDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {

    @Headers("Content-Type: application/json")
    @POST("api/v1/users")
    suspend fun createUser(
        @Body body: UserCreateRequest,
    ): Response<Unit>

    @GET("api/v1/users/me")
    suspend fun getCurrentUser(
        @Header("Authorization") jwtHeader: String,
    ): Response<UserDetailResponse>

    @DELETE("api/v1/users/me")
    suspend fun deleteCurrentUser(
        @Header("Authorization") jwtHeader: String
    ) : Response<Unit>
}