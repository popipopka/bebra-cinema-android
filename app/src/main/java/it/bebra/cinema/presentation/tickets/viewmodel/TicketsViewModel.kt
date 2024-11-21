package it.bebra.cinema.presentation.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.domain.model.TicketListModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime
import java.time.Month
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor() : ViewModel() {
    private val _resultResponsesFlow: MutableStateFlow<List<TicketListModel>> =
        MutableStateFlow(listOf())
    val resultResponses = _resultResponsesFlow.asLiveData()

    fun loadTickets() {
        _resultResponsesFlow.value = mockData()
    }

    private fun mockData(): List<TicketListModel> {
        return listOf(
            TicketListModel(
                1,
                "Один из нас",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.JULY, 9, 20, 8),
                19,
                1
            ),
            TicketListModel(
                2,
                "Мы",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.JUNE, 11, 20, 8),
                25,
                2
            ),
            TicketListModel(
                3,
                "Невероятные приключения крутого супергероя",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.MAY, 15, 20, 8),
                2,
                3
            ),
            TicketListModel(
                4,
                "Очень страшное и длинное название фильма, такого вы точно не видели",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.APRIL, 17, 20, 8),
                18,
                4
            ),
            TicketListModel(
                5,
                "Галка",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.MARCH, 12, 15, 32),
                143,
                5
            ),
            TicketListModel(
                6,
                "Маколей Калкин",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.FEBRUARY, 1, 1, 8),
                14,
                6
            ),
            TicketListModel(
                7,
                "Виу виу",
                "https://image.openmoviedb.com/kinopoisk-images/10835644/e5e66936-58d5-426a-b860-d29f9bcc2311/orig",
                LocalDateTime.of(2024, Month.JANUARY, 25, 10, 43),
                2,
                7
            ),
        )
    }
}