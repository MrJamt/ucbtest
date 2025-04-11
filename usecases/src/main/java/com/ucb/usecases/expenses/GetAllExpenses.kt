package com.ucb.usecases.expenses

import com.ucb.data.expense.ExpenseRepository
import com.ucb.domain.Expense

class GetAllExpenses(
    private val expenseRepository: ExpenseRepository,
) {
    suspend operator fun invoke(): List<Expense> = expenseRepository.getExpenses()
}
