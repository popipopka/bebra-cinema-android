package it.bebra.cinema.app.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.cinema.app.common.emitInIO
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.auth.LoginRequest
import it.bebra.cinema.domain.port.`in`.LoginInputPort
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginInputPort: LoginInputPort
) : ViewModel() {

    private val _loginResultFlow = MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val resultFlow = _loginResultFlow.asLiveData()

    fun login(data: LoginRequest) = _loginResultFlow.emitInIO(viewModelScope) {
        loginInputPort.invoke(data)
    }
}