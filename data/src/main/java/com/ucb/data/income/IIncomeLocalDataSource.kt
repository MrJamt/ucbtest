package com.ucb.data.income

import com.ucb.domain.Income

interface IIncomeLocalDataSource {
    suspend fun saveIncome(income: Income): Boolean

    suspend fun getIncomes(): List<Income>

    suspend fun deleteIncome(income: Income)
}
