@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.ucb.framework.room.dao

import androidx.room.*
import com.ucb.framework.room.entity.IncomeEntity

@Dao
interface IncomeDao {
    @Insert
    suspend fun insert(income: IncomeEntity): Long

    @Query("SELECT * FROM incomes ORDER BY date DESC")
    suspend fun getAll(): List<IncomeEntity>

    @Delete
    suspend fun delete(income: IncomeEntity)
}
