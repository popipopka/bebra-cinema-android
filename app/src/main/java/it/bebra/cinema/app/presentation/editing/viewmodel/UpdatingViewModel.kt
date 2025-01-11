package it.bebra.cinema.app.presentation.editing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.cinema.app.common.emitInIO
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.user.UserDetailResponse
import it.bebra.cinema.domain.dto.user.UserUpdateRequest
import it.bebra.cinema.domain.port.`in`.GetUserProfileInputPort
import it.bebra.cinema.domain.port.`in`.UpdateUserInputPort
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UpdatingViewModel @Inject constructor(
    private val updateUserInputPort: UpdateUserInputPort,
    private val getUserProfileInputPort: GetUserProfileInputPort
) : ViewModel() {
    private val _updateResultFlow = MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val updateResultFlow = _updateResultFlow.asLiveData()

    private val _userProfileResultFlow =
        MutableStateFlow<Resource<UserDetailResponse>>(Resource.Loading)
    val userProfileResultFlow = _userProfileResultFlow.asLiveData()

    fun updateUser(data: UserUpdateRequest) = _updateResultFlow.emitInIO(viewModelScope) {
        updateUserInputPort.invoke(data)
    }

    fun loadUserProfile() = _userProfileResultFlow.emitInIO(viewModelScope) {
        getUserProfileInputPort.invoke()
    }
}