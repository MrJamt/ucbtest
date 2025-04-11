package com.ucb.data.expense

import com.ucb.domain.Expense

interface IExpenseLocalDataSource {
    suspend fun saveExpense(expense: Expense): Boolean
}
