package com.example.myapplication

data class AppUIState(
    val monthlyIncome: Double = 0.0,
    val wishToSaveAmount: String = "0.0",
    val numberOfBills: Int = 0,
    val timeWindow : Int = 0,
    val timeWindowDropDownMenuExpandedState : Boolean = false,


    val savingAmount: Double = 0.0,
    val timeWindowUnitArray: Array<String> = arrayOf<String>("Day(s)", "Week(s)", "Year(s)"),
    val timeWindowUnit: String = timeWindowUnitArray[0]
)