package it.bebra.cinema.domain.dto.page

data class PageResponse<T>(
    val items: List<T>,
    val cursors: Map<String, String>,
    val hasMore: Boolean
)
