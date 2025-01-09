package it.bebra.cinema.remote.retrofit

fun getJwtAuthHeader(token: String): String {
    return "Bearer $token"
}