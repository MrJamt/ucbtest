package com.ucb.domain.repository

import com.ucb.domain.Movie

interface IMovieRepository {
    suspend fun getMovies(apiKey: String): List<Movie>
}
