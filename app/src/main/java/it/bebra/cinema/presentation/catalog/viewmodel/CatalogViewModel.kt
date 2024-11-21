package it.bebra.cinema.presentation.catalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.domain.model.MovieListModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor() : ViewModel() {
    private val _resultResponsesFlow: MutableStateFlow<List<MovieListModel>> =
        MutableStateFlow(listOf())
    val resultResponses = _resultResponsesFlow.asLiveData()

    fun loadFilms() {
        _resultResponsesFlow.value = mockData()
    }

    private fun mockData(): List<MovieListModel> {
        return listOf(
            MovieListModel(
                1,
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                "Фильм 1",
                100
            ),
            MovieListModel(
                1,
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                "Фильм другой 2",
                200
            ),
            MovieListModel(
                1,
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                "Фильм точно другой 3",
                10
            ),
            MovieListModel(
                1,
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                "Фильм верьте 4",
                50
            ),
            MovieListModel(
                1,
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                "Фильм фильм 5",
                60
            ),
            MovieListModel(
                1,
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                "Фильм мультфильм 6",
                20
            ),
            MovieListModel(
                1,
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                "Очень длинное название у фильма под номером 7",
                120
            )
        )
    }
}