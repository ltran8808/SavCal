package com.example.myapplication

import android.R
import android.R.attr.text
import android.R.attr.value
import android.app.Activity
import android.provider.Settings.Global.getString
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R.string




@Composable
fun AppScreen(
    // Pass the data section in State and ViewModel lesson
    appViewModel: AppViewModel = viewModel()
){
    val savingAmount = appViewModel.viewModelSavingAmount



    AppLayOut(
        onWishToSaveAmountChanged = {appViewModel.updateWishToSave(it)},
        wishToSaveAmount = appViewModel.stateWishToSaveAmount,
        onTimeWindowChanged = {appViewModel.updateTimeWindow(it)},
        timeWindow = appViewModel.stateTimeWindow,
        selectedTimeWindowUnitParam = appViewModel.stateTimeWindowUnit,
        onTimeWindowUnitChangedParam = {appViewModel.updateTimeWindowUnit(it)},
        onSubmitButtonClick = {appViewModel.calculateSaving()},
        onResetButtonClick = {appViewModel.reset()},
        showDialog = {appViewModel.updateShowDialog()}
    )

    if (appViewModel.stateShowDialog == true){
        FinalResultDialog(
            savingAmount = savingAmount,
            timeWindowUnit = appViewModel.stateTimeWindowUnit,
            onCoolButtonClick = {appViewModel.updateShowDialog()}
        )
    }
}

@Composable
fun AppLayOut(
    onWishToSaveAmountChanged: (String) -> Unit,
    wishToSaveAmount: String,
    onTimeWindowChanged: (String) -> Unit,
    timeWindow: String,
    selectedTimeWindowUnitParam: String,
    onTimeWindowUnitChangedParam: (String) -> Unit,
    onSubmitButtonClick : () -> Unit,
    onResetButtonClick : () -> Unit,
    showDialog : () -> Unit

){


    Column(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        TextField(
            value = wishToSaveAmount,
            onValueChange = onWishToSaveAmountChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            label={Text(
                stringResource(string.wish_to_save_amount),
                fontSize = 8.sp)},
            modifier = Modifier
            .padding(bottom = 16.dp),
        )

        TimeWindowLayout(
            expanded = false,
            timeWindow = timeWindow,
            timeWindowUnitArraysParam = arrayOf("Day(s)", "Week(s)", "Month(s)", "Year(s)"),
            onTimeWindowChanged = onTimeWindowChanged,
            selectedTimeWindowUnitParam = selectedTimeWindowUnitParam,
            onTimeWindowUnitChangedParam = onTimeWindowUnitChangedParam

        )

        Button(
            onClick = {
                onSubmitButtonClick()
                showDialog()
            },
            modifier = Modifier
                .padding(bottom = 8.dp)
        ) {
            Text(text= stringResource(string.submit))
        }

        Button(
            onClick = {onResetButtonClick()},
            modifier = Modifier

        ) {
            Text(text= stringResource(string.reset))
        }



    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeWindowLayout(
    expanded : Boolean,
    timeWindow: String,
    timeWindowUnitArraysParam: Array<String>,
    selectedTimeWindowUnitParam: String,
    onTimeWindowChanged: (String) -> Unit,
    onTimeWindowUnitChangedParam: (String) -> Unit

){





    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .width(280.dp)
            .padding(bottom = 16.dp)

    ){
        TextField(
            value = timeWindow,
            onValueChange = onTimeWindowChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            label = {Text (
                stringResource(string.saving_window),
                fontSize = 8.sp
            )},

            modifier = Modifier
                .width(120.dp)
                .padding(end = 4.dp)
        )

        TimeWindowUnit_ExposedDropdownMenu(
            expanded = expanded,
            timeWindowUnitArraysParam = timeWindowUnitArraysParam,
            onTimeWindowUnitChangedParam = onTimeWindowUnitChangedParam,
            selectedTimeWindowUnitParam = selectedTimeWindowUnitParam
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeWindowUnit_ExposedDropdownMenu(
    expanded : Boolean = false,
    timeWindowUnitArraysParam : Array<String>,
    onTimeWindowUnitChangedParam: (String) -> Unit,
    selectedTimeWindowUnitParam : String
){
    //You need these properties here for the below functions to access the data
    var expanded : Boolean by remember {mutableStateOf(expanded)}
    var selectedTimeWindowUnit : String by remember {mutableStateOf(selectedTimeWindowUnitParam)}


    Box(
        modifier = Modifier
            .wrapContentWidth()
            .padding(start = 8.dp)
    ){
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ){
            TextField (
                value = selectedTimeWindowUnit,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false}
            ){
                timeWindowUnitArraysParam.forEach{ timeWindowUnit ->
                    DropdownMenuItem(
                        text = {Text (text = timeWindowUnit)},
                        onClick = {
                            selectedTimeWindowUnit = timeWindowUnit
                            onTimeWindowUnitChangedParam(timeWindowUnit)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview(){
    AppScreen()
}

@Composable
private fun FinalResultDialog(
    savingAmount: Double,
    timeWindowUnit: String,
    onCoolButtonClick : () -> Unit,
    modifier : Modifier = Modifier
){
    val activity = LocalActivity.current as Activity


    
    AlertDialog(
        onDismissRequest = {},
        title = { Text(stringResource(string.alert_dialog_title)) },
        text = { Text(stringResource(string.alertdialogtext, savingAmount.toString(), timeWindowUnit)) },

        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                        activity.finish()
                    }
                ) {
                    Text(text = stringResource(string.exit))
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onCoolButtonClick()
                    }
                ) {
                    Text(text = stringResource(string.cool))
                }
            }
        )

}


