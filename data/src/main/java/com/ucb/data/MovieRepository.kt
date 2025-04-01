package com.ucb.data

import com.ucb.data.NetworkResult
import com.ucb.data.git.IMovieRemoteDataSource
import com.ucb.domain.Movie

class MovieRepository(
    private val remoteDataSource: IMovieRemoteDataSource,
) {
    suspend fun getPopularMovies(): NetworkResult<List<Movie>> = remoteDataSource.fetchMovies()
}
