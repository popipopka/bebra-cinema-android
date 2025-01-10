package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.dto.movie.MovieListResponse

interface GetAllMoviesInputPort {
    suspend fun invoke(lastId: Int?, limit: Int?, query: String?): Resource<PageResponse<MovieListResponse>>
}