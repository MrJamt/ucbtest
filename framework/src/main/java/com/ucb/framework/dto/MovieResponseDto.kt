package com.ucb.framework.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponseDto(
    @Json(name = "results")
    val movies: List<MovieDto>,
)

@JsonClass(generateAdapter = true)
data class MovieDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val poster_path: String,
    @Json(name = "release_date")
    val release_date: String,
    @Json(name = "vote_average")
    val vote_average: Double,
)
