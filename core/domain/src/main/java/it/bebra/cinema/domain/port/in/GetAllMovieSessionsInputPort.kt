package it.bebra.cinema.domain.port.`in`

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.session.SessionListResponse

interface GetAllMovieSessionsInputPort {
    suspend fun invoke(movieId: Int): Resource<List<SessionListResponse>>
}