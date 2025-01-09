package it.bebra.cinema.domain

import android.util.Log

fun handleErrorResult(className: String, result: Resource<Any?>) {
    if(result is Resource.Error) {
        Log.e(className, result.msg)
    }
}

fun toNonNullResult() {

}