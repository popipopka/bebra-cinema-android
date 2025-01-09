package it.bebra.cinema.domain.dto.movie

data class MovieDetailResponse(
    val id: Int,
    val name: String,
    val year: Int,
    val genres: List<String>,
    val description: String,
    val duration: Int,
    val posterUrl: String?
)