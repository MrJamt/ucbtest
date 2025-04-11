package com.ucb.data.expense

import com.ucb.domain.Expense

class ExpenseRepository(
    private val localDataSource: IExpenseLocalDataSource
) {
    suspend fun save(expense: Expense): Boolean {
        return localDataSource.saveExpense(expense)
    }
}