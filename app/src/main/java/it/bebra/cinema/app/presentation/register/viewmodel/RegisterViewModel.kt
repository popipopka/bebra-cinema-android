package it.bebra.cinema.app.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.cinema.app.common.emitInIO
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.user.UserCreateRequest
import it.bebra.cinema.domain.port.`in`.CreateUserInputPort
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserInputPort: CreateUserInputPort
) : ViewModel() {
    private val _registerResultFlow = MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val registerResultFlow = _registerResultFlow.asLiveData()

    fun register(data: UserCreateRequest) = _registerResultFlow.emitInIO(viewModelScope) {
        createUserInputPort.invoke(data)
    }
}