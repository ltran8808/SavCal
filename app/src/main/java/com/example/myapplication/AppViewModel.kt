package com.example.myapplication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.properties.Delegates


class AppViewModel() : ViewModel() {

    var stateSavingGoal by mutableStateOf("")
    private set
    var stateNumberOfBills by mutableStateOf("")
    private set
    var stateIncome by mutableStateOf("")
    private set

    //Backing Property Section in State and ViewModel Lession
    private val _appUIState : MutableStateFlow<AppUIState> = MutableStateFlow(AppUIState())
//    val appUIState : StateFlow<AppUIState> = _appUIState.asStateFlow()

    //Display Random Scrambled Word in State and ViewModel Lesson


    var viewModelSavingAmount by mutableStateOf(0.0)
    private set

    var stateShowDialog by mutableStateOf(false)
    private set

    //Passing and Receiving data from the AppScreen.kt (ViewModel and State in Compose>Architecting your
    //compose UI>Display the guess word
    var stateWishToSaveAmount by mutableStateOf("")
    private set
    var stateTimeWindow by mutableStateOf("")
    private set
    var stateTimeWindowUnit by mutableStateOf("Day(s)")
    private set


    fun calculateSaving(){
        println("ViewModel calculateSaving() ran")
        viewModelSavingAmount = stateWishToSaveAmount.toDouble() / stateTimeWindow.toDouble()


    }


    fun updateWishToSave(wishToSaveAmount: String){
        stateWishToSaveAmount = wishToSaveAmount

    }

    fun updateTimeWindow(timeWindow: String){
        stateTimeWindow = timeWindow
    }

    fun updateTimeWindowUnit(timeWindowUnit: String){
        stateTimeWindowUnit = timeWindowUnit
    }

    fun updateShowDialog(){
        if(stateShowDialog == true) {
            stateShowDialog = false
        }else{
            stateShowDialog = true
        }
    }

    fun reset(){
        stateWishToSaveAmount = ""
        stateTimeWindow = ""
        stateTimeWindowUnit = "Day(s)"
    }

    fun updateIncome(income: String){
        stateIncome = income
    }

    fun updateNumberOfBills(numberOfBills: String) {
        stateNumberOfBills = numberOfBills
    }

    fun updateSavingGoal(savingGoal: String) {
        stateSavingGoal = savingGoal
    }


}