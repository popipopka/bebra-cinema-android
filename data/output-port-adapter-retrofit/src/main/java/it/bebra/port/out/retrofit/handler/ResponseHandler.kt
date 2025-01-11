package it.bebra.port.out.retrofit.handler


import android.util.Log
import it.bebra.cinema.domain.Resource
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ResponseHandler {
    operator fun <T> invoke(response: Response<T>) = try {
        handleResponse(response)

    } catch (e: Exception) {
        Log.e(javaClass.simpleName, e.toString())

        val errorCode = mapExceptionToErrorCode(e)

        Resource.Error(getErrorMessage(errorCode))
    }

    private fun <T> handleResponse(response: Response<T>): Resource<T> {
        return if (response.isSuccessful) {
            response.body()?.let {
                Resource.Success(it)
            } ?: Resource.Empty

        } else {
            if (response.code() == 401) {
                Resource.Unauthorized
            } else {
                Resource.Error(getErrorMessage(response.code()))
            }
        }
    }

    private fun getErrorMessage(code: Int) = when (code) {
        ErrorCodes.UnknownHost.code -> "No connection"
        ErrorCodes.SocketTimeOut.code -> "Timeout"
        403 -> "Forbidden"
        in 400..499 -> "Check entered data. Code: $code"
        in 500..599 -> "Error with connecting to server. Code: $code"
        else -> "Something went wrong"
    }

    private fun mapExceptionToErrorCode(e: Exception): Int {
        return when (e) {
            is SocketTimeoutException -> ErrorCodes.SocketTimeOut.code
            is UnknownHostException,
            is ConnectException -> ErrorCodes.UnknownHost.code

            else -> Int.MAX_VALUE
        }
    }

    private enum class ErrorCodes(val code: Int) {
        SocketTimeOut(-1),
        UnknownHost(-2)
    }
}