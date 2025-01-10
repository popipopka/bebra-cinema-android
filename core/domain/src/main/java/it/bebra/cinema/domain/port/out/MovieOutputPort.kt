package it.bebra.cinema.domain.port.out

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.dto.movie.MovieDetailResponse
import it.bebra.cinema.domain.dto.movie.MovieListResponse

interface MovieOutputPort {
    suspend fun getAllMovies(
        token: String,
        lastId: Int?,
        limit: Int?,
        query: String?
    ): Resource<PageResponse<MovieListResponse>>

    suspend fun getMovie(token: String, id: Int): Resource<MovieDetailResponse>
}