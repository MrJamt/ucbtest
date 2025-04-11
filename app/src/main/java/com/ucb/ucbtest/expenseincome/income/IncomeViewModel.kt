package com.ucb.ucbtest.expenseincome.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Income
import com.ucb.usecases.incomes.DeleteIncome
import com.ucb.usecases.incomes.GetAllIncomes
import com.ucb.usecases.incomes.RegisterIncome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel
    @Inject
    constructor(
        private val registerIncome: RegisterIncome,
        private val getAllIncomes: GetAllIncomes,
        private val deleteIncome: DeleteIncome,
    ) : ViewModel() {
        sealed class IncomeUIState {
            object Loading : IncomeUIState()

            class Loaded(
                val incomes: List<Income>,
            ) : IncomeUIState()

            class Error(
                val message: String,
            ) : IncomeUIState()
        }

        private val _state = MutableStateFlow<IncomeUIState>(IncomeUIState.Loading)
        val state: StateFlow<IncomeUIState> = _state

        // Función para cargar todos los ingresos
        fun loadIncomes() {
            _state.value = IncomeUIState.Loading
            viewModelScope.launch {
                try {
                    // Obtener los ingresos desde el LocalDataSource
                    val incomes = getAllIncomes.invoke() // Este debería devolver una lista de ingresos desde la base de datos
                    _state.value = IncomeUIState.Loaded(incomes)
                } catch (e: Exception) {
                    _state.value = IncomeUIState.Error("Error al cargar los ingresos: ${e.message}")
                }
            }
        }

        // Función para agregar un ingreso
        fun addIncome(income: Income) {
            viewModelScope.launch {
                try {
                    registerIncome.invoke(income) // Registrar el ingreso en el LocalDataSource
                    loadIncomes() // Recargar los ingresos después de agregar uno nuevo
                } catch (e: Exception) {
                    _state.value = IncomeUIState.Error("Error al agregar el ingreso: ${e.message}")
                }
            }
        }

        // Función para eliminar un ingreso
        fun removeIncome(income: Income) {
            viewModelScope.launch {
                try {
                    deleteIncome.invoke(income) // Eliminar el ingreso del LocalDataSource
                    loadIncomes() // Recargar los ingresos después de eliminar uno
                } catch (e: Exception) {
                    _state.value = IncomeUIState.Error("Error al eliminar el ingreso: ${e.message}")
                }
            }
        }
    }
