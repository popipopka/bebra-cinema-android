package it.bebra.cinema.domain.dto.movie

data class MovieListResponse(
    val id: Int,
    val posterUrl: String? = null,
    val name: String,
    val duration: Int
)