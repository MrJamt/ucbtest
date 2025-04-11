package com.ucb.usecases

import com.ucb.domain.Expense

class RegisterExpense(
    val expenseRepository: ExpenseRepository,
) {
    suspend operator fun invoke(expense: Expense) = expenseRepository.saveExpense(expense)
}
