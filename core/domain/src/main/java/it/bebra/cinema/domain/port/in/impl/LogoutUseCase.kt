package it.bebra.cinema.domain.port.`in`.impl

import it.bebra.cinema.domain.port.`in`.LogoutInputPort
import it.bebra.cinema.domain.service.storage.EncryptedStorage
import it.bebra.cinema.domain.service.storage.StorageKeys

class LogoutUseCase(
    private val storage: EncryptedStorage
) : LogoutInputPort {

    override suspend fun invoke() {
        storage.apply {
            remove(StorageKeys.ACCESS_TOKEN)
            remove(StorageKeys.REFRESH_TOKEN)
        }
    }
}