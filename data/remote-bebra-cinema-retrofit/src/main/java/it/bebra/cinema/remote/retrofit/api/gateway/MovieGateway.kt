package it.bebra.cinema.remote.retrofit.api.gateway

import it.bebra.cinema.remote.retrofit.api.MovieApi
import it.bebra.cinema.remote.retrofit.getJwtAuthHeader

class MovieGateway(
    private val movieApi: MovieApi
) {

    suspend fun getAllMovies(accessToken: String, lastId: Int?, limit: Int?, query: String?) =
        movieApi.getAllMovies(getJwtAuthHeader(accessToken), lastId, limit, query)

    suspend fun getMovie(accessToken: String, id: Int) =
        movieApi.getMovie(getJwtAuthHeader(accessToken), id)
}