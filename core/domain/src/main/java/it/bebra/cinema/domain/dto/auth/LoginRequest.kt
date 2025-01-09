package it.bebra.cinema.domain.dto.auth

data class LoginRequest (
    val username: String,
    val password: String
)