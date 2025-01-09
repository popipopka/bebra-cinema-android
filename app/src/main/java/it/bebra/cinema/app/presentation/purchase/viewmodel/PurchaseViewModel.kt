package it.bebra.cinema.app.presentation.purchase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.cinema.app.common.emitInIO
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.ticket.TicketCreateRequest
import it.bebra.cinema.domain.port.`in`.CreateTicketInputPort
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val createTicketInputPort: CreateTicketInputPort
) : ViewModel() {
    private val _purchaseResultFlow = MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val purchaseResultFlow = _purchaseResultFlow.asLiveData()

    fun createTicket(data: TicketCreateRequest) = _purchaseResultFlow.emitInIO(viewModelScope) {
        createTicketInputPort.invoke(data)
    }
}