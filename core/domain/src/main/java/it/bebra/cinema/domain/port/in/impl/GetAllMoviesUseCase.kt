package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.movie.MovieListResponse
import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.handleErrorResult
import it.bebra.cinema.domain.port.`in`.GetAllMoviesInputPort
import it.bebra.cinema.domain.port.out.MovieOutputPort
import it.bebra.cinema.domain.service.request.manager.RequestManager
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class GetAllMoviesUseCase(
    private val movieOutputPort: MovieOutputPort,
    private val storage: EncryptedStorage,
    private val requestManager: RequestManager
) : GetAllMoviesInputPort {
    override suspend fun invoke(lastId: Int?, limit: Int?, query: String?): Resource<PageResponse<MovieListResponse>> =
        requestManager {
            val token = storage.getString(StorageKeys.ACCESS_TOKEN)
            val result = movieOutputPort.getAllMovies(token!!, lastId, limit, query)

            handleErrorResult(javaClass.simpleName, result)

            result
        }
}