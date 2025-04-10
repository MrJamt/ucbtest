package com.ucb.framework.mappers

import com.ucb.domain.Gitalias
import com.ucb.domain.Movie
import com.ucb.framework.dto.AvatarResponseDto
import com.ucb.framework.dto.MovieDto
import com.ucb.framework.persistence.GitAccount

fun AvatarResponseDto.toModel(): Gitalias =
    Gitalias(
        login = login,
        avatarUrl = url,
    )

fun Gitalias.toEntity(): GitAccount = GitAccount(login)

fun GitAccount.toModel(): Gitalias =
    Gitalias(
        alias,
        "",
    )

fun MovieDto.toModel(): Movie =
    Movie(
        title = title,
        overview = overview,
        posterPath = posterPath,
    )
