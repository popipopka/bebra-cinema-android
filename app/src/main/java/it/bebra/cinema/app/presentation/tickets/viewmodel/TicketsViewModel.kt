package it.bebra.cinema.app.presentation.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.cinema.app.common.emitInIO
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.page.PageResponse
import it.bebra.cinema.domain.dto.page.TicketPageCursor
import it.bebra.cinema.domain.dto.ticket.TicketListResponse
import it.bebra.cinema.domain.port.`in`.DeleteTicketInputPort
import it.bebra.cinema.domain.port.`in`.GetAllTicketsInputPort
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val getAllTicketUseCase: GetAllTicketsInputPort,
    private val deleteTicketInputPort: DeleteTicketInputPort
) : ViewModel() {
    companion object {
        private const val DEFAULT_TICKETS_PAGE_LIMIT = 10
    }

    private val _ticketsResultFlow =
        MutableStateFlow<Resource<PageResponse<TicketListResponse>>>(Resource.Loading)
    val ticketsResultFlow = _ticketsResultFlow.asLiveData()

    private val _deleteResultFlow =
        MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val deleteResultFlow = _deleteResultFlow.asLiveData()

    var tickets: MutableList<TicketListResponse> = mutableListOf()
    private var cursorLastId: Int? = null
    private var hasMoreTickets: Boolean = true

    fun loadTickets() {
        if (!hasMoreTickets) {
            return
        }

        _ticketsResultFlow.emitInIO(viewModelScope) {
            getAllTicketUseCase.invoke(cursorLastId, DEFAULT_TICKETS_PAGE_LIMIT).also { resource ->
                resource.handle(
                    onSuccess = {
                        tickets += it.items
                        cursorLastId = it.cursors[TicketPageCursor.LAST_ID.value]?.toInt()
                        hasMoreTickets = it.hasMore
                    },
                    onEmpty = {
                        hasMoreTickets = false
                    }
                )
            }
        }
    }

    fun deleteTicket(id: Int) = _deleteResultFlow.emitInIO(viewModelScope) {
        deleteTicketInputPort.invoke(id)
    }

    fun resetState() {
        tickets = mutableListOf()
        cursorLastId = null
        hasMoreTickets = true
    }
}