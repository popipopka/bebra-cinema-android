package it.bebra.cinema.domain.dto.user

data class UserCreateRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String,
    val password: String,
)