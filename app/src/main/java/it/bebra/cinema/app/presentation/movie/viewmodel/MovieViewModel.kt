package it.bebra.cinema.app.presentation.movie.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.cinema.app.common.emitInIO
import it.bebra.cinema.app.common.util.getSystemZonedDateTime
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.movie.MovieDetailResponse
import it.bebra.cinema.domain.dto.session.SessionListResponse
import it.bebra.cinema.domain.port.`in`.GetAllMovieSessionsInputPort
import it.bebra.cinema.domain.port.`in`.GetMovieInputPort
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieInputPort: GetMovieInputPort,
    private val getAllMovieSessionInputPort: GetAllMovieSessionsInputPort
) : ViewModel() {

    private val _movieResultFlow =
        MutableStateFlow<Resource<MovieDetailResponse>>(Resource.Loading)
    val resultFlow = _movieResultFlow.asLiveData()

    private val _sessionResultFlow =
        MutableStateFlow<Resource<List<SessionListResponse>>>(Resource.Loading)
    val sessionResultFlow = _sessionResultFlow.asLiveData()

    val monthlySessionsList: MutableList<MonthlySessions> = mutableListOf()

    fun loadMovie(id: Int) = _movieResultFlow.emitInIO(viewModelScope) {
        getMovieInputPort.invoke(id)
    }

    fun loadMovieSession(id: Int) = _sessionResultFlow.emitInIO(viewModelScope) {
        getAllMovieSessionInputPort.invoke(id).also { resource ->
            resource.handle(
                onSuccess = { fillMonthlySessionsList(it) }
            )
        }
    }

    private fun fillMonthlySessionsList(sessions: List<SessionListResponse>) {
        monthlySessionsList.addAll(createMonthlySessionMap(sessions).values)
    }

    private fun createMonthlySessionMap(
        sessions: List<SessionListResponse>
    ): MutableMap<Month, MonthlySessions> {
        val monthlySessionsMap = mutableMapOf<Month, MonthlySessions>()

        for (elem in sessions) {
            val date: LocalDate = getSystemZonedDateTime(elem.startTime).toLocalDate()

            val month = date.month
            val dayOfMonth = date.dayOfMonth
            val dayOfWeek = date.dayOfWeek

            val monthlySessions = monthlySessionsMap.computeIfAbsent(month) {
                MonthlySessions(month, mutableListOf())
            }
            val dailySessionsList = monthlySessions.days
            val session = Session(elem.id, elem.hallName, elem.startTime, elem.price)

            if (dailySessionsList.isEmpty()) {
                dailySessionsList.add(DailySessions(dayOfMonth, dayOfWeek, mutableListOf(session)))
                continue
            }

            val dailySessions = findDailySessionsByDayOfMonth(dailySessionsList, dayOfMonth)

            dailySessions?.sessions?.add(session) ?: dailySessionsList.add(
                DailySessions(dayOfMonth, dayOfWeek, mutableListOf(session))
            )
        }

        return monthlySessionsMap
    }

    private fun findDailySessionsByDayOfMonth(
        dailySessionsList: List<DailySessions>,
        dayOfMonth: Int
    ): DailySessions? {
        val dailySessions = dailySessionsList[dailySessionsList.size - 1]

        return if (dailySessions.dayOfMonth == dayOfMonth) dailySessions else null
    }

    data class MonthlySessions(
        val month: Month,
        val days: MutableList<DailySessions>
    )

    data class DailySessions(
        val dayOfMonth: Int,
        val dayOfWeek: DayOfWeek,
        val sessions: MutableList<Session>
    )

    data class Session(
        val id: Int,
        val hallName: String,
        val startTime: String,
        val price: Float
    )
}