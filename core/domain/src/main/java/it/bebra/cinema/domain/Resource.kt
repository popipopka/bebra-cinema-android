package it.bebra.cinema.domain

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val msg: String) : Resource<Nothing>()
    data object Empty : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    data object Unauthorized : Resource<Nothing>()

    inline fun handle(
        onSuccess: (data: T) -> Unit = {},
        onError: (msg: String) -> Unit = {},
        onEmpty: () -> Unit = {},
        onLoading: () -> Unit = {},
        onUnauthorized: () -> Unit = {}
    ) {
        when (this) {
            is Success -> onSuccess(data)
            is Error -> onError(msg)
            is Empty -> onEmpty()
            is Loading -> onLoading()
            Unauthorized -> onUnauthorized()
        }
    }
}