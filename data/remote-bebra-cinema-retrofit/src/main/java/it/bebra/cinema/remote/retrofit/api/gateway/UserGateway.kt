package it.bebra.cinema.remote.retrofit.api.gateway

import it.bebra.cinema.domain.dto.user.UserCreateRequest
import it.bebra.cinema.remote.retrofit.api.UserApi
import it.bebra.cinema.remote.retrofit.getJwtAuthHeader

class UserGateway(
    private val userApi: UserApi
) {

    suspend fun createUser(user: UserCreateRequest) =
        userApi.createUser(user)

    suspend fun getUserProfile(accessToken: String) =
        userApi.getCurrentUser(getJwtAuthHeader(accessToken))
}