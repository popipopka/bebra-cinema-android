package it.bebra.port.out.retrofit

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.movie.MovieDetailResponse
import it.bebra.cinema.domain.dto.movie.MovieListResponse
import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.port.out.MovieOutputPort
import it.bebra.cinema.remote.retrofit.api.gateway.MovieGateway
import it.bebra.port.out.retrofit.handler.ResponseHandler

class RetrofitMovieGatewayAdapter(
    private val retrofitMovieGateway: MovieGateway,
    private val handler: ResponseHandler
) : MovieOutputPort {

    override suspend fun getAllMovies(
        token: String,
        lastId: Int?,
        limit: Int?
    ): Resource<PageResponse<MovieListResponse>> =
        handler(retrofitMovieGateway.getAllMovies(token, lastId, limit))

    override suspend fun getMovie(token: String, id: Int): Resource<MovieDetailResponse> =
        handler(retrofitMovieGateway.getMovie(token, id))
}