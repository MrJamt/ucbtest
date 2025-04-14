package com.ucb.data.income

import com.ucb.domain.Income

class IncomeRepository(
    val incomeLocalDataSource: IIncomeLocalDataSource,
) {
    suspend fun saveIncome(income: Income): Boolean = this.incomeLocalDataSource.saveIncome(income)

    suspend fun getIncomes(): List<Income> = this.incomeLocalDataSource.getIncomes()

    suspend fun deleteIncome(income: Income) = this.incomeLocalDataSource.deleteIncome(income)
}
