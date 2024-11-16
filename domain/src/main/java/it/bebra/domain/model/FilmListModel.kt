package it.bebra.domain.model

data class FilmListModel(
    val id: Int,
    val posterUrl: String?,
    val name: String,
    val duration: Int
)