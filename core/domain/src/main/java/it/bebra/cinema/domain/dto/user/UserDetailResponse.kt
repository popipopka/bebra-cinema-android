package it.bebra.cinema.domain.dto.user

data class UserDetailResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val createdTime: String,
    val updatedTime: String,
)