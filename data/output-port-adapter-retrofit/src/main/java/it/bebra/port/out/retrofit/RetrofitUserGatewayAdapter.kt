package it.bebra.port.out.retrofit

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.user.UserCreateRequest
import it.bebra.cinema.domain.dto.user.UserDetailResponse
import it.bebra.cinema.domain.port.out.UserOutputPort
import it.bebra.cinema.remote.retrofit.api.gateway.UserGateway
import it.bebra.port.out.retrofit.handler.ResponseHandler

class RetrofitUserGatewayAdapter(
    private val retrofitUserGateway: UserGateway,
    private val handler: ResponseHandler
) : UserOutputPort {
    override suspend fun createUser(user: UserCreateRequest): Resource<Unit> =
        handler(retrofitUserGateway.createUser(user))

    override suspend fun getUserProfile(token: String): Resource<UserDetailResponse> =
        handler(retrofitUserGateway.getUserProfile(token))

    override suspend fun deleteUser(token: String): Resource<Unit> =
        handler(retrofitUserGateway.deleteCurrentUser(token))
}