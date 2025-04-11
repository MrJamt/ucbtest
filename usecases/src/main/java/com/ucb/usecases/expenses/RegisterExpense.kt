package com.ucb.usecases.expenses

import com.ucb.data.expense.ExpenseRepository
import com.ucb.domain.Expense

class RegisterExpense(
    private val expenseRepository: ExpenseRepository,
) {
    suspend fun invoke(expense: Expense) = expenseRepository.saveExpense(expense)
}
