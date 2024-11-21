package it.bebra.domain.model

data class MovieListModel(
    val id: Int,
    val posterUrl: String?,
    val name: String,
    val duration: Int
)