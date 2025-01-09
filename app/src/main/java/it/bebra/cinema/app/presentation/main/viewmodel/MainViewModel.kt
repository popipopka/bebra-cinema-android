package it.bebra.cinema.app.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.bebra.cinema.domain.port.`in`.IsUserLoggedInInputPort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInInputPort
) : ViewModel() {

    fun isLogin(): Boolean {
        var result: Boolean

        runBlocking {
            result = withContext(Dispatchers.IO) {
                isUserLoggedInUseCase.invoke()
            }
        }

        return result
    }
}