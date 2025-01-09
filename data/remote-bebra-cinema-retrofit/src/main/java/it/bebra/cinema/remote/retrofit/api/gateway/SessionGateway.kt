package it.bebra.cinema.remote.retrofit.api.gateway

import it.bebra.cinema.remote.retrofit.api.SessionApi
import it.bebra.cinema.remote.retrofit.getJwtAuthHeader

class SessionGateway(
    private val sessionApi: SessionApi
) {

    suspend fun getAllMovieSession(accessToken: String, movieId: Int) =
        sessionApi.getAllMovieSessions(getJwtAuthHeader(accessToken), movieId)
}