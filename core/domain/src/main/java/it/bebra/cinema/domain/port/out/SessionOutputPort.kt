package it.bebra.cinema.domain.port.out

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.session.SessionListResponse

interface SessionOutputPort {
    suspend fun getAllMovieSessions(token: String, movieId: Int): Resource<List<SessionListResponse>>
}