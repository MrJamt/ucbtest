package com.ucb.data.remote.dto

data class MovieResponse(
    val results: List<MovieDto>,
)

data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double,
)
