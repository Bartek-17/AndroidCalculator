package com.example.androidcalculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun AdvancedCalculatorApp(navController: NavController) {
    var currentInput by rememberSaveable { mutableStateOf("0") }
    var operand1 by rememberSaveable { mutableStateOf<String?>(null) }
    var operator by rememberSaveable { mutableStateOf("") }
    var justClickedOperator by rememberSaveable { mutableStateOf(false) }

    val maxLength = 20
    val clearButtonLabel = if (currentInput == "0" && operand1 == null) "AC" else "C"

    // 7 rows, 4 columns
    val verticalButtons = listOf(
        "sin", "cos", "tan", "ln",
        "log", "√", "x²", "x^y",
        clearButtonLabel, "%", "⌫", "÷",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "+/-", "0", ".", "="
    )

    // 4 rows, 7 columns
    val horizontalButtons = listOf(
        "sin", "cos", "tan", "7", "8", "9", "÷",
        "ln", "log", "√", "4", "5", "6", "x",
        "x²", "x^y", "%", "1", "2", "3", "-",
        clearButtonLabel, "⌫", "+/-", "0", ".", "=", "+"
    )

    AdvancedCalculatorScreen(
        display = currentInput,
        clearButtonLabel = clearButtonLabel,
        onButtonClick = { label ->
            if ((currentInput == "Input too long" || currentInput == "Result too long" || currentInput == "Error") && label != "AC" && label != "C" && label != "⌫") {
                return@AdvancedCalculatorScreen
            }

            when (label) {
                "C" -> {
                    if (currentInput == "0") {
                        operand1 = null
                        operator = ""
                        justClickedOperator = false
                    } else {
                        currentInput = "0"
                    }
                }
                "AC" -> {
                    currentInput = "0"
                    operand1 = null
                    operator = ""
                    justClickedOperator = false
                }
                in "0".."9" -> {
                    if (justClickedOperator) {
                        currentInput = label
                        justClickedOperator = false
                    } else if (currentInput.length < maxLength) {
                        currentInput = if (currentInput == "0") label else currentInput + label
                    } else {
                        currentInput = "Input too long"
                    }
                }
                "." -> {
                    if (justClickedOperator) {
                        currentInput = "0."
                        justClickedOperator = false
                    } else if (!currentInput.contains(".")) {
                        if (currentInput.length < maxLength) {
                            currentInput += "."
                        } else {
                            currentInput = "Input too long"
                        }
                    }
                }
                "+/-" -> {
                    if (currentInput != "0") {
                        if (currentInput.startsWith("-")) {
                            currentInput = currentInput.substring(1)
                        } else if (currentInput.length < maxLength) {
                            currentInput = "-$currentInput"
                        } else {
                            currentInput = "Input too long"
                        }
                    }
                    justClickedOperator = false
                }
                "%" -> {
                    if (operand1 != null && (operator == "+" || operator == "-")) {
                        val operand1Decimal = operand1?.toBigDecimalOrNull() ?: return@AdvancedCalculatorScreen
                        val currentInputDecimal = currentInput.toBigDecimalOrNull() ?: return@AdvancedCalculatorScreen
                        val percentageValue = operand1Decimal.multiply(currentInputDecimal).divide(100.toBigDecimal())
                        currentInput = performCalculation(percentageValue.toPlainString(), null, "format", maxLength)
                    } else if (operand1 != null) {
                        val currentInputDecimal = currentInput.toBigDecimalOrNull() ?: return@AdvancedCalculatorScreen
                        val percentageValue = currentInputDecimal.divide(100.toBigDecimal())
                        currentInput = performCalculation(percentageValue.toPlainString(), null, "format", maxLength)
                    } else {
                        val result = performCalculation(currentInput, null, "%", maxLength)
                        currentInput = result
                    }
                }
                // single argument operators
                in listOf("sin", "cos", "tan", "ln", "log", "√", "x²") -> {
                    val result = performCalculation(currentInput, null, label, maxLength)
                    currentInput = result
                    operand1 = null // Reset after unary operation
                    justClickedOperator = false
                }
                // two arguments operators
                in listOf("+", "-", "x", "÷", "x^y") -> {
                    if (justClickedOperator) {
                        operator = label
                        return@AdvancedCalculatorScreen
                    }
                    if (operand1 == null) {
                        operand1 = currentInput
                        operator = label
                    } else {
                        val result = performCalculation(operand1, currentInput, operator, maxLength)
                        currentInput = result
                        operand1 = result
                        operator = label
                    }
                    justClickedOperator = true
                }
                "=" -> {
                    if (operand1 != null && !justClickedOperator) {
                        val result = performCalculation(operand1, currentInput, operator, maxLength)
                        currentInput = result
                        operand1 = null
                        operator = ""
                    } else if (operand1 == null) {
                        val result = performCalculation(currentInput, null, "format", maxLength)
                        currentInput = result
                    }
                    justClickedOperator = false
                }
                "⌫" -> {
                    if (currentInput == "Input too long" || currentInput == "Result too long" || currentInput == "Error") {
                        currentInput = "0"
                        operand1 = null
                        operator = ""
                        justClickedOperator = false
                    } else if (!justClickedOperator) {
                        currentInput = if (currentInput.length > 1) currentInput.dropLast(1) else "0"
                    }
                }
            }
        },
        navController = navController,
        verticalButtons = verticalButtons,
        horizontalButtons = horizontalButtons
    )
}
