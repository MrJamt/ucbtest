package com.ucb.usecases.incomes

import com.ucb.data.income.IncomeRepository
import com.ucb.domain.Income

class GetAllIncomes(
    private val incomeRepository: IncomeRepository,
) {
    suspend operator fun invoke(): List<Income> = incomeRepository.getIncomes()
}
