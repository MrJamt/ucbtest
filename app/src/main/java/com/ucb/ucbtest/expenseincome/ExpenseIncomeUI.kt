@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.ucb.ucbtest.expenseincome

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucb.domain.Expense
import com.ucb.domain.Income
import com.ucb.ucbtest.expenseincome.expense.ExpenseViewModel
import com.ucb.ucbtest.expenseincome.income.IncomeViewModel

@Suppress("ktlint:standard:function-naming")
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ExpenseIncomeUI(
    expenseViewModel: ExpenseViewModel = viewModel(),
    incomeViewModel: IncomeViewModel = viewModel(),
) {
    LaunchedEffect(Unit) {
        expenseViewModel.loadExpenses()
        incomeViewModel.loadIncomes()
    }

    val expenseState = expenseViewModel.state.value
    val incomeState = incomeViewModel.state.value
    val context = LocalContext.current // Obtener el contexto

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Register Expense / Income", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Simple form inputs for Expense/Income
        var name = ""
        var price = ""
        var description = ""
        var date = ""

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = description, onValueChange = {
            description = it
        }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    val expense = Expense(0, name, price.toDouble(), description, date)
                    expenseViewModel.addExpense(expense)
                },
                modifier = Modifier.weight(1f),
            ) {
                Text("Add Expense")
            }

            Button(
                onClick = {
                    val income = Income(0, name, price.toDouble(), description, date)
                    incomeViewModel.addIncome(income)
                },
                modifier = Modifier.weight(1f),
            ) {
                Text("Add Income")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Display Expenses
        Text(text = "Expenses", style = MaterialTheme.typography.bodyLarge)
        when (expenseState) {
            is ExpenseViewModel.ExpenseUIState.Loading -> {
                CircularProgressIndicator()
            }
            is ExpenseViewModel.ExpenseUIState.Loaded -> {
                ExpenseList(expenses = expenseState.expenses) { expense ->
                    expenseViewModel.removeExpense(expense)
                    Toast.makeText(context, "Expense Removed", Toast.LENGTH_SHORT).show()
                }
            }
            is ExpenseViewModel.ExpenseUIState.Error -> {
                Text("Error: ${expenseState.message}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Incomes
        Text(text = "Incomes", style = MaterialTheme.typography.bodyLarge)
        when (incomeState) {
            is IncomeViewModel.IncomeUIState.Loading -> {
                CircularProgressIndicator()
            }
            is IncomeViewModel.IncomeUIState.Loaded -> {
                IncomeList(incomes = incomeState.incomes) { income ->
                    incomeViewModel.removeIncome(income)
                    Toast.makeText(context, "Income Removed", Toast.LENGTH_SHORT).show()
                }
            }
            is IncomeViewModel.IncomeUIState.Error -> {
                Text("Error: ${incomeState.message}")
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ExpenseList(
    expenses: List<Expense>,
    onDelete: (Expense) -> Unit,
) {
    Column {
        expenses.forEach { expense ->
            ElevatedCard(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { /* Handle click for expense details */ },
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(expense.name)
                        Text("${expense.price} - ${expense.date}")
                    }
                    IconButton(onClick = { onDelete(expense) }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun IncomeList(
    incomes: List<Income>,
    onDelete: (Income) -> Unit,
) {
    Column {
        incomes.forEach { income ->
            ElevatedCard(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { /* Handle click for income details */ },
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(income.name)
                        Text("${income.price} - ${income.date}")
                    }
                    IconButton(onClick = { onDelete(income) }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpenseIncomeUI()
}
