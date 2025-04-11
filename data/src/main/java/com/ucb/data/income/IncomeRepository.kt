package com.ucb.data.income

import com.ucb.domain.Income

class IncomeRepository(
    private val localDataSource: IIncomeLocalDataSource,
) {
    suspend fun saveIncome(income: Income): Boolean = localDataSource.saveIncome(income)

    suspend fun getIncomes(): List<Income> = localDataSource.getIncomes()

    suspend fun deleteIncome(income: Income) = localDataSource.deleteIncome(income)
}
