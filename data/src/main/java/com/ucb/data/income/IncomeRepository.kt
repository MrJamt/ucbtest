package com.ucb.data.income

import com.ucb.domain.Income

class IncomeRepository(
    private val localDataSource: IIncomeLocalDataSource,
) {
    suspend fun save(income: Income): Boolean = localDataSource.saveIncome(income)
}
