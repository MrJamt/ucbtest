package com.ucb.data.expense

import com.ucb.domain.Expense

class ExpenseRepository(
    private val localDataSource: IExpenseLocalDataSource,
) {
    suspend fun saveExpense(expense: Expense): Boolean = localDataSource.saveExpense(expense)

    suspend fun getExpenses(): List<Expense> = localDataSource.getExpenses()

    suspend fun deleteExpense(expense: Expense) = localDataSource.deleteExpense(expense)
}
