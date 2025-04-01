package com.ucb.data.git

import com.ucb.data.NetworkResult
import com.ucb.domain.Movie

interface IMovieRemoteDataSource {
    suspend fun fetchMovies(): NetworkResult<List<Movie>>
}
