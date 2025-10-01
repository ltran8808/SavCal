package com.example.myapplication.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.AppViewModel

@Composable
fun AppScreen2(
    appViewModel: AppViewModel = viewModel()
){
    AppScreen2Layout(
        viewModelIncome = appViewModel.stateIncome,
        viewModelOnIncomeChanged = appViewModel.updateIncome(),
        viewModelNumberOfBills = appViewModel.stateNumberOfBills,
        viewModelOnNumberOfBillsChanged = appViewModel.updateNumberOfBills(),
        viewModelSavingGoal = appViewModel.stateSavingGoal,
        viewModelOnSavingGoalChanged = appViewModel.updateSavingGoal()
    )
}

@Composable
fun AppScreen2Layout(
    viewModelIncome: String,
    viewModelOnIncomeChanged: () -> Unit,
    viewModelNumberOfBills: String,
    viewModelOnNumberOfBillsChanged: () -> Unit,
    viewModelSavingGoal: String,
    viewModelOnSavingGoalChanged: () -> Unit
){
    Column(
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        InputFields(
        income = viewModelIncome,
        onIncomeChanged = viewModelOnIncomeChanged,
        numberOfBills = viewModelNumberOfBills,
        onNumberOfBillsChanged = viewModelOnNumberOfBillsChanged,
        savingGoal = viewModelSavingGoal,
        onSavingGoalChanged = viewModelOnSavingGoalChanged
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(onClick = {

        }){
            Text("Submit")
        }

        Spacer(modifier = Modifier.padding(4.dp))

        Button(onClick = {

        }) {
            Text("Reset")
        }}

}
@Composable
fun InputFields(
    income: String,
    onIncomeChanged: () -> Unit,
    numberOfBills: String,
    onNumberOfBillsChanged: () -> Unit,
    savingGoal: String,
    onSavingGoalChanged: () -> Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            value = income,
            onValueChange = {
                onIncomeChanged()
            },
            label = {Text("Income",
                fontSize = 8.sp)}
        )

        TextField(
            value = numberOfBills,
            onValueChange = {
                onNumberOfBillsChanged()
            },
            label = {Text("Number of Bills",
                fontSize = 8.sp)}
        )

        TextField(
            value = savingGoal,
            onValueChange = {
                onSavingGoalChanged()
            },
            label = {Text("Saving Goal",
                fontSize = 8.sp)}
        )
    }

}

@Preview
@Composable
fun AppScreen2Preview(){
    AppScreen2()
}