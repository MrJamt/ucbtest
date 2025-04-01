package com.ucb.usecases

import com.ucb.domain.Movie
import com.ucb.domain.repository.IMovieRepository

class GetMoviesUseCase(
    private val repository: IMovieRepository,
) {
    suspend fun execute(apiKey: String): List<Movie> = repository.getMovies(apiKey)
}
