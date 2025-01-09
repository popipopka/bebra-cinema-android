package it.bebra.cinema.domain.port.`in`

interface IsUserLoggedInInputPort {
    suspend fun invoke(): Boolean
}