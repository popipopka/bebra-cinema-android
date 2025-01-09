package it.bebra.cinema.domain.service.request.manager

import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.port.`in`.GetNewAccessTokenInputPort
import it.bebra.cinema.domain.port.`in`.IsUserLoggedInInputPort
import it.bebra.cinema.domain.port.`in`.LoginInputPort
import it.bebra.cinema.domain.service.request.chain.impl.AccessTokenFetcher
import it.bebra.cinema.domain.service.request.chain.impl.AuthorizationChecker
import it.bebra.cinema.domain.service.request.chain.impl.InputPortExecutor
import it.bebra.cinema.domain.service.request.chain.impl.RefreshTokenFetcher
import it.bebra.cinema.domain.service.storage.EncryptedStorage

class RequestManagerImpl(
    private var storage: EncryptedStorage,
    private val isUserLoggedInInputPort: IsUserLoggedInInputPort,
    private var getNewAccessToken: GetNewAccessTokenInputPort,
    private var loginInputPort: LoginInputPort
) : RequestManager {

    override suspend fun <T> invoke(operation: suspend () -> Resource<T>): Resource<T> {
        val authorizationChecker = AuthorizationChecker<T>(isUserLoggedInInputPort)
        val inputPortExecutor = InputPortExecutor(operation)
        val accessTokenFetcher = AccessTokenFetcher<T>(getNewAccessToken)
        val refreshTokenFetcher = RefreshTokenFetcher<T>(loginInputPort, storage)

        val chain = authorizationChecker
            .setNextSuccess(
                inputPortExecutor
                    .setNextFailure(
                        accessTokenFetcher
                            .setNextSuccess(
                                inputPortExecutor
                            )
                            .setNextFailure(
                                refreshTokenFetcher
                                    .setNextSuccess(inputPortExecutor)
                            )
                    )
            )

        return chain.handle()
    }
}