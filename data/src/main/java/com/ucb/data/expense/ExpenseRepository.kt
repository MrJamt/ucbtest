package com.ucb.data.expense

import com.ucb.domain.Expense

class ExpenseRepository(
    val expenseLocalDataSource: IExpenseLocalDataSource,
) {
    suspend fun saveExpense(expense: Expense): Boolean = this.expenseLocalDataSource.saveExpense(expense)

    suspend fun getExpenses(): List<Expense> = this.expenseLocalDataSource.getExpenses()

    suspend fun deleteExpense(expense: Expense) = this.expenseLocalDataSource.deleteExpense(expense)
}
