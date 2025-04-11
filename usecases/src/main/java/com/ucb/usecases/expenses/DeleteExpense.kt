package com.ucb.usecases.expenses

import com.ucb.data.expense.ExpenseRepository
import com.ucb.domain.Expense

class DeleteExpense(
    private val expenseRepository: ExpenseRepository,
) {
    suspend fun invoke(expense: Expense) = expenseRepository.deleteExpense(expense)
}
