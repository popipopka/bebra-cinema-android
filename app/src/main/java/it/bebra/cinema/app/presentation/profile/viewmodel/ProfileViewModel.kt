package it.bebra.cinema.app.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.cinema.app.common.emitInIO
import it.bebra.cinema.domain.Resource
import it.bebra.cinema.domain.dto.user.UserDetailResponse
import it.bebra.cinema.domain.port.`in`.DeleteUserInputPort
import it.bebra.cinema.domain.port.`in`.GetUserProfileInputPort
import it.bebra.cinema.domain.port.`in`.LogoutInputPort
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileInputPort: GetUserProfileInputPort,
    private val logoutInputPort: LogoutInputPort,
    private val deleteUserInputPort: DeleteUserInputPort
) : ViewModel() {
    private val _userProfileResultFlow =
        MutableStateFlow<Resource<UserDetailResponse>>(Resource.Loading)
    val userProfileResultFlow = _userProfileResultFlow.asLiveData()

    private val _deleteUserResultFlow =
        MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val deleteUserResultFlow = _deleteUserResultFlow.asLiveData(

    )
    fun getUserProfile() = _userProfileResultFlow.emitInIO(viewModelScope) {
        getUserProfileInputPort.invoke()
    }

    fun logout() = viewModelScope.launch {
        logoutInputPort.invoke()
    }

    fun deleteUser() =_deleteUserResultFlow.emitInIO(viewModelScope) {
        deleteUserInputPort.invoke()
    }
}