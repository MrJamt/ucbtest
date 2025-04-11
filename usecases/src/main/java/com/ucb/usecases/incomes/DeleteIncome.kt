package com.ucb.usecases.incomes

import com.ucb.data.income.IncomeRepository
import com.ucb.domain.Income

class DeleteIncome(
    private val incomeRepository: IncomeRepository,
) {
    suspend operator fun invoke(income: Income) = incomeRepository.deleteIncome(income)
}
