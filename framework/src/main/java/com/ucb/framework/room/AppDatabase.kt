package com.ucb.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ucb.framework.room.dao.ExpenseDao
import com.ucb.framework.room.dao.IncomeDao
import com.ucb.framework.room.entity.ExpenseEntity
import com.ucb.framework.room.entity.IncomeEntity

@Database(entities = [ExpenseEntity::class, IncomeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    abstract fun incomeDao(): IncomeDao

    companion object {
        @Volatile
        var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "expenses_incomes")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
