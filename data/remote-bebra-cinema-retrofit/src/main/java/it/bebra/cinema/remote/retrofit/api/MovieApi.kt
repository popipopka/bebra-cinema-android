package it.bebra.cinema.remote.retrofit.api

import it.bebra.cinema.domain.dto.movie.MovieDetailResponse
import it.bebra.cinema.domain.dto.movie.MovieListResponse
import it.bebra.cinema.domain.dto.page.PageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/api/v1/movies")
    suspend fun getAllMovies(
        @Header("Authorization") jwtHeader: String,
        @Query("lastId") lastId: Int?,
        @Query("limit") limit: Int?,
        @Query("query") query: String?
    ) : Response<PageResponse<MovieListResponse>>

    @GET("api/v1/movies/{id}")
    suspend fun getMovie(
        @Header("Authorization") jwtHeader: String,
        @Path("id") id: Int,
    ): Response<MovieDetailResponse>
}