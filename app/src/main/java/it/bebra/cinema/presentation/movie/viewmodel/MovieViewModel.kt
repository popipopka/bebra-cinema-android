package it.bebra.cinema.presentation.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import it.bebra.domain.model.MovieDetailModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MovieViewModel @Inject constructor() : ViewModel() {
    private val _resultResponsesFlow: MutableStateFlow<MovieDetailModel> =
        MutableStateFlow(
            MovieDetailModel(
                id = -1,
                name = "null",
                posterUrl = "",
                year = 2024,
                genres = listOf(),
                description = "null",
                duration = 1,
                producer = "null"
            )
        )
    val resultResponses = _resultResponsesFlow.asLiveData()

    fun loadMovie(id: Int) {
        _resultResponsesFlow.value = mockData(id)
    }

    private fun mockData(id: Int): MovieDetailModel {
        return listOf(
            MovieDetailModel(
                id = 1,
                name = "Фильм 1",
                year = 2024,
                genres = listOf("Комедия", "Ранком", "Романтика", "Ужасы"),
                description = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. ",
                duration = 100,
                posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                producer = "Квентин Джозаф Тарантино"
            ),
            MovieDetailModel(
                id = 2,
                name = "Фильм другой 2",
                year = 2024,
                genres = listOf(
                    "Комедия",
                    "Ранком",
                    "Романтика",
                    "Ужасы",
                    "Драма",
                    "Фантастика",
                    "Фэнтези",
                    "Исторический"
                ),
                description = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",
                duration = 200,
                posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                producer = "Хэй Гей Ричи"
            ),
            MovieDetailModel(
                id = 3,
                name = "Фильм точно другой Фильм точно другой 3",
                year = 2024,
                genres = listOf("Комедия", "Ранком"),
                description = "Lorem ipsum doloLorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.  Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.  r sit amet, consectetuer adipiscing elit. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.  Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.  Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.  Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.  Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.  Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.  Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt.",
                duration = 10,
                posterUrl = "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                producer = "Стивен \"Теперь Продюссер\" Спиллберг"
            )
        )[id - 1]
    }
}