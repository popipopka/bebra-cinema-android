package it.bebra.cinema.domain.dto.user

data class UserUpdateRequest(
    val firstName: String,
    val lastName: String,
    val email: String
)