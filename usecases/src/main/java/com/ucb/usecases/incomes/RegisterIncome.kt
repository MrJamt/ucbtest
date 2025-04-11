package com.ucb.usecases.incomes

import com.ucb.data.income.IncomeRepository
import com.ucb.domain.Income

class RegisterIncome(
    val incomeRepository: IncomeRepository,
) {
    suspend fun invoke(income: Income) = incomeRepository.saveIncome(income)
}
