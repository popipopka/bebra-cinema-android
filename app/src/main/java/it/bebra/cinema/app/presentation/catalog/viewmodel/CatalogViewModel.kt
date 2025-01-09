package it.bebra.cinema.app.presentation.catalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.cinema.app.common.emitInIO
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.movie.MovieListResponse
import it.bebra.cinema.domain.dto.page.MoviePageCursor
import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.port.`in`.GetAllMoviesInputPort
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesInputPort
) : ViewModel() {
    companion object {
        private const val DEFAULT_MOVIES_PAGE_LIMIT = 10
    }

    private val _moviesResultFlow =
        MutableStateFlow<Resource<PageResponse<MovieListResponse>>>(Resource.Loading)
    val resultFlow = _moviesResultFlow.asLiveData()

    var movies: List<MovieListResponse> = mutableListOf()
    private var cursorLastId: Int? = null
    private var hasMoreMovies: Boolean = true

    fun loadMovies() {
        if (!hasMoreMovies) {
            return
        }

        _moviesResultFlow.emitInIO(viewModelScope) {
            getAllMoviesUseCase
                .invoke(cursorLastId, DEFAULT_MOVIES_PAGE_LIMIT).also { resource ->
                    resource.handle(
                        onSuccess = {
                            movies += it.items
                            cursorLastId = it.cursors[MoviePageCursor.LAST_ID.value]?.toInt()
                            hasMoreMovies = it.hasMore
                        },
                        onEmpty = {
                            hasMoreMovies = false
                        }
                    )
                }

        }
    }


}