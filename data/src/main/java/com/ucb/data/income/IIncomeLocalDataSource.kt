package com.ucb.data.income

import com.ucb.domain.Income

interface IIncomeLocalDataSource {
    suspend fun saveIncome(income: Income): Boolean
}
