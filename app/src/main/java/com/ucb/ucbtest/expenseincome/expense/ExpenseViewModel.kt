package com.ucb.ucbtest.expenseincome.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Expense
import com.ucb.usecases.expenses.DeleteExpense
import com.ucb.usecases.expenses.GetAllExpenses
import com.ucb.usecases.expenses.RegisterExpense
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel
    @Inject
    constructor(
        private val registerExpense: RegisterExpense,
        private val getAllExpenses: GetAllExpenses,
        private val deleteExpense: DeleteExpense,
    ) : ViewModel() {
        sealed class ExpenseUIState {
            object Loading : ExpenseUIState()

            data class Loaded(
                val expenses: List<Expense>,
            ) : ExpenseUIState()

            class Error(
                val message: String,
            ) : ExpenseUIState()
        }

        private val _state = MutableStateFlow<ExpenseUIState>(ExpenseUIState.Loading)
        val state: StateFlow<ExpenseUIState> = _state

        // Función para cargar todos los gastos
        fun loadExpenses() {
            _state.value = ExpenseUIState.Loading
            viewModelScope.launch {
                try {
                    // Obtener los gastos desde el LocalDataSource
                    val expenses = getAllExpenses.invoke() // Este debería devolver una lista de gastos desde la base de datos
                    _state.value = ExpenseUIState.Loaded(expenses)
                } catch (e: Exception) {
                    _state.value = ExpenseUIState.Error("Error  : ${e.message}")
                }
            }
        }

        // Función para agregar un gasto
        fun addExpense(expense: Expense) {
            viewModelScope.launch {
                try {
                    registerExpense.invoke(expense)
                    loadExpenses()
                } catch (e: Exception) {
                    _state.value = ExpenseUIState.Error("Error: ${e.message}")
                }
            }
        }

        // Función para eliminar un gasto
        fun removeExpense(expense: Expense) {
            viewModelScope.launch {
                try {
                    deleteExpense.invoke(expense) // Eliminar el gasto del LocalDataSource
                    loadExpenses() // Recargar los gastos después de eliminar uno
                } catch (e: Exception) {
                    _state.value = ExpenseUIState.Error("Error al eliminar el gasto: ${e.message}")
                }
            }
        }
    }
