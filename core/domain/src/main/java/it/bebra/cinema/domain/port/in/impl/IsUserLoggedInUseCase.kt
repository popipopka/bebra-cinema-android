package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.port.`in`.IsUserLoggedInInputPort
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class IsUserLoggedInUseCase(
    private val storage: EncryptedStorage
) : IsUserLoggedInInputPort {
    override suspend fun invoke(): Boolean {
        val refreshToken = storage.getString(StorageKeys.REFRESH_TOKEN)

        return refreshToken != null
    }
}