package it.bebra.cinema.domain.dto.auth

data class LoginResponse (
    val accessToken: String,
    val refreshToken: String,
)