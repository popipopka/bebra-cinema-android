package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.movie.MovieDetailResponse

interface GetMovieInputPort {
    suspend fun invoke(id: Int): Resource<MovieDetailResponse>
}