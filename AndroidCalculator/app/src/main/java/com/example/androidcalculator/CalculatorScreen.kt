package com.example.androidcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun CalculatorScreen(
    display: String,
    clearButtonLabel: String,
    onButtonClick: (String) -> Unit,
    navController: NavController
){
    val verticalButtons = listOf(
        clearButtonLabel, "%", "⌫", "÷",
        "7", "8", "9", "x",
        "4" , "5", "6", "-",
        "1", "2" , "3", "+",
        "+/-","0",".","="
    )

    val horizontalButtons = listOf(
        "7", "8", "9", "÷", clearButtonLabel,
        "4", "5", "6", "x", "⌫",
        "1", "2", "3", "-", "+",
        "0", ".", "+/-", "%", "="
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("Simple Calculator") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { innerPadding ->
        BoxWithConstraints(modifier = Modifier.padding(innerPadding)) {
            val isLandscape = maxWidth > maxHeight

            if (isLandscape) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DisplayArea(display, modifier = Modifier.weight(1f))
                    CalculatorButtonsGrid(horizontalButtons, 5, onButtonClick, modifier = Modifier.weight(1f))
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    DisplayArea(display, modifier = Modifier.weight(0.4f))  // takes 40% of vertical space
                    HorizontalDivider()
                    CalculatorButtonsGrid(verticalButtons, 4, onButtonClick, modifier = Modifier.weight(0.6f))
                }
            }
        }
    }
}
