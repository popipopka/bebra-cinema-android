package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.session.SessionListResponse
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.GetAllMovieSessionsInputPort
import it.bebra.cinema.domain.port.out.SessionOutputPort
import it.bebra.cinema.domain.service.request.manager.RequestManager
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class GetAllMovieSessionsUseCase(
    private val sessionOutputPort: SessionOutputPort,
    private val storage: EncryptedStorage,
    private val requestManager: RequestManager
) : GetAllMovieSessionsInputPort {

    override suspend fun invoke(movieId: Int): Resource<List<SessionListResponse>> =
        requestManager {
            val token = storage.getString(StorageKeys.ACCESS_TOKEN)

            val result = sessionOutputPort.getAllMovieSessions(token!!, movieId)

            handleErrorResult(javaClass.simpleName, result)

            result
        }
}