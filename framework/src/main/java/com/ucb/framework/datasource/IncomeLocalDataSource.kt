package com.ucb.framework.datasource

import android.content.Context
import com.ucb.data.income.IIncomeLocalDataSource
import com.ucb.domain.Income
import com.ucb.framework.mappers.toEntity
import com.ucb.framework.mappers.toModel
import com.ucb.framework.room.AppDatabase
import com.ucb.framework.room.dao.IncomeDao

class IncomeLocalDataSource(
    context: Context,
) : IIncomeLocalDataSource {
    val incomeDao: IncomeDao = AppDatabase.getDatabase(context).incomeDao()

    override suspend fun saveIncome(income: Income): Boolean = incomeDao.insert(income.toEntity()) > 0

    override suspend fun getIncomes(): List<Income> = incomeDao.getAll().map { it.toModel() }

    override suspend fun deleteIncome(income: Income) {
        incomeDao.delete(income.toEntity())
    }
}
