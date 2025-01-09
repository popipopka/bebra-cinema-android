package it.bebra.port.out.retrofit

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.session.SessionListResponse
import it.bebra.cinema.domain.port.out.SessionOutputPort
import it.bebra.cinema.remote.retrofit.api.gateway.SessionGateway
import it.bebra.port.out.retrofit.handler.ResponseHandler

class RetrofitSessionGatewayAdapter(
    private val retrofitSessionGateway: SessionGateway,
    private val handler: ResponseHandler
) : SessionOutputPort {
    override suspend fun getAllMovieSessions(
        token: String,
        movieId: Int
    ): Resource<List<SessionListResponse>> =
        handler(retrofitSessionGateway.getAllMovieSession(token, movieId))
}