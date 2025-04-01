package com.ucb.data.remote.dto

import com.ucb.domain.Movie

fun MovieDto.toDomain(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        poster_path = "https://image.tmdb.org/t/p/w185/$poster_path",
        release_date = release_date,
        vote_average = vote_average,
    )
