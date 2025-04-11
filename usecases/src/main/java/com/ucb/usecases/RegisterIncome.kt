package com.ucb.usecases

import com.ucb.domain.Income

class RegisterIncome(
    val incomeRepository: IncomeRepository,
) {
    suspend operator fun invoke(income: Income) = incomeRepository.saveIncome(income)
}
