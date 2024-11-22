package it.bebra.domain.model

data class MovieDetailModel (
    val id: Int,
    val posterUrl: String,
    val name: String,
    val genres: List<String>,
    val duration: Int,
    val year: Int,
    val producer: String,
    val description: String
)