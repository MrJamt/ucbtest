package com.ucb.data.expense

import com.ucb.domain.Expense

interface IExpenseLocalDataSource {
    suspend fun saveExpense(expense: Expense): Boolean

    suspend fun getExpenses(): List<Expense>

    suspend fun deleteExpense(expense: Expense)
}
