package com.ucb.framework.mappers

import com.ucb.domain.Expense
import com.ucb.domain.Gitalias
import com.ucb.domain.Income
import com.ucb.domain.Movie
import com.ucb.framework.dto.AvatarResponseDto
import com.ucb.framework.dto.MovieDto
import com.ucb.framework.persistence.GitAccount
import com.ucb.framework.room.entity.ExpenseEntity
import com.ucb.framework.room.entity.IncomeEntity

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

fun Expense.toEntity(): ExpenseEntity =
    ExpenseEntity(
        id = this.id,
        name = this.name,
        price = this.price,
        description = this.description,
        date = this.date,
    )

fun ExpenseEntity.toModel(): Expense =
    Expense(
        id = this.id,
        name = this.name,
        price = this.price,
        description = this.description,
        date = this.date,
    )

fun Income.toEntity(): IncomeEntity =
    IncomeEntity(
        id = this.id,
        name = this.name,
        price = this.price,
        description = this.description,
        date = this.date,
    )

fun IncomeEntity.toModel(): Income =
    Income(
        id = this.id,
        name = this.name,
        price = this.price,
        description = this.description,
        date = this.date,
    )
