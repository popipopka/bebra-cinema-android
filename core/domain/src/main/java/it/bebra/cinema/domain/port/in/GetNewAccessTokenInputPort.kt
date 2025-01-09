package it.bebra.cinema.domain.port.`in`

interface GetNewAccessTokenInputPort {
    suspend fun invoke(): Boolean
}