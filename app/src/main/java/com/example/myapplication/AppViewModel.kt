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

    //Backing Property Section in State and ViewModel Lession
    private val _appUIState : MutableStateFlow<AppUIState> = MutableStateFlow(AppUIState())
    val appUIState : StateFlow<AppUIState> = _appUIState.asStateFlow()

    //Display Random Scrambled Word in State and ViewModel Lession
    var savingAmount : Double = appUIState.value.savingAmount
    var viewModelSavingAmount : Double = 0.0

    //Passing and Receiving data from the AppScreen.kt (ViewModel and State in Compose>Architecting your
    //compose UI>Display the guess word
    var stateWishToSaveAmount by mutableStateOf("0.0")
    private set
    var stateTimeWindow by mutableStateOf("0")
    private set
    var stateTimeWindowUnit by mutableStateOf("Day(s)")
    private set


    fun calculateSaving(wishToSaveAmount : Double, timeWindow : Int){
//        viewModelSavingAmount = stateWishToSaveAmount.toDouble() / stateTimeWindow.toDouble()

        viewModelSavingAmount = wishToSaveAmount / timeWindow

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


}