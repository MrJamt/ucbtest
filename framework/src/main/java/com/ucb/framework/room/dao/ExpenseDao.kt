@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.ucb.framework.room.dao

import androidx.room.*
import com.ucb.framework.room.entity.ExpenseEntity

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(expense: ExpenseEntity): Long

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    suspend fun getAll(): List<ExpenseEntity>

    @Delete
    suspend fun delete(expense: ExpenseEntity)
}
