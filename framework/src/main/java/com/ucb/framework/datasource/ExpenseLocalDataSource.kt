package com.ucb.framework.datasource

import android.content.Context
import com.ucb.data.expense.IExpenseLocalDataSource
import com.ucb.domain.Expense
import com.ucb.framework.mappers.toEntity
import com.ucb.framework.mappers.toModel
import com.ucb.framework.room.AppDatabase
import com.ucb.framework.room.dao.ExpenseDao

class ExpenseLocalDataSource(
    val context: Context,
) : IExpenseLocalDataSource {
    val expenseDao: ExpenseDao = AppDatabase.getDatabase(context).expenseDao()

    override suspend fun saveExpense(expense: Expense): Boolean {
        expenseDao.insert(expense.toEntity()) > 0
        return true
    }

    override suspend fun getExpenses(): List<Expense> = expenseDao.getAll().map { it.toModel() }

    override suspend fun deleteExpense(expense: Expense) {
        expenseDao.delete(expense.toEntity())
    }
}
