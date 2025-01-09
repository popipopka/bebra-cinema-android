package it.bebra.cinema.remote.retrofit.api

import it.bebra.cinema.domain.dto.session.SessionListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SessionApi {

    @GET("/api/v1/movies/{movieId}/sessions")
    suspend fun getAllMovieSessions(
        @Header("Authorization") jwtHeader: String,
        @Path("movieId") movieId: Int?
    ): Response<List<SessionListResponse>>
}