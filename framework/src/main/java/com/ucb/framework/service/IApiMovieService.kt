package com.ucb.framework.service

import com.ucb.framework.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiMovieService {
    @GET("/3/discover/movie?sort_by=popularity.desc&api_key=fa3e844ce31744388e07fa47c7c5d8c3")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
    ): MovieResponseDto
}
