package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.movie.MovieDetailResponse
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.GetMovieInputPort
import it.bebra.cinema.domain.port.out.MovieOutputPort
import it.bebra.cinema.domain.service.request.manager.RequestManager
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class GetMovieUseCase(
    private val movieOutputPort: MovieOutputPort,
    private val storage: EncryptedStorage,
    private val requestManager: RequestManager
) : GetMovieInputPort {
    override suspend fun invoke(id: Int): Resource<MovieDetailResponse> = requestManager {
        val token = storage.getString(StorageKeys.ACCESS_TOKEN)

        val result = movieOutputPort.getMovie(token!!, id)

        handleErrorResult(javaClass.simpleName, result)

        result
    }
}