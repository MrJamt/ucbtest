package com.ucb.usecases.expenses

import com.ucb.data.expense.ExpenseRepository
import com.ucb.domain.Expense

class RegisterExpense(
    val expenseRepository: ExpenseRepository,
) {
    suspend operator fun invoke(expense: Expense) = expenseRepository.saveExpense(expense)
}
