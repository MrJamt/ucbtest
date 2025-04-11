package com.ucb.domain

data class Expense(
    val id: Int = 0,
    val name: String,
    val price: Double,
    val description: String,
    val date: String,
)
