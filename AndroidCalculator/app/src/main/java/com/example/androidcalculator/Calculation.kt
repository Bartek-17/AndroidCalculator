package com.example.androidcalculator

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.*

fun performCalculation(
    operand1String: String?,
    operand2String: String?,
    operator: String,
    maxLength: Int
): String {
    if (operator == "format") {
        val operand = operand1String?.trim()
        if (operand.isNullOrEmpty()) return "0"

        val numberString = if (operand.endsWith(".")) {
            operand.dropLast(1)
        } else {
            operand
        }

        if (numberString.isEmpty()) return "0"

        val resultString = numberString.toBigDecimalOrNull()?.stripTrailingZeros()?.toPlainString() ?: return "Error"
        val integerPart = resultString.split(".").first()

        if (integerPart.length > maxLength) {
            return "Result too long"
        }

        if (resultString.length > maxLength) {
            var truncatedResult = resultString.substring(0, maxLength)
            if (truncatedResult.endsWith(".")) {
                truncatedResult = truncatedResult.dropLast(1)
            }
            return truncatedResult
        }
        return resultString
    }

    val cleanOperand1String = operand1String?.removeSuffix(".")
    val cleanOperand2String = operand2String?.removeSuffix(".")

    val operand1 = cleanOperand1String?.toBigDecimalOrNull() ?: return "Error"
    val operand2 = cleanOperand2String?.toBigDecimalOrNull()

    val result = try {
        when (operator) {
            // two arguments operators
            "+" -> operand1.add(operand2)
            "-" -> operand1.subtract(operand2)
            "x" -> operand1.multiply(operand2)
            "÷" -> if (operand2 != null && operand2.compareTo(BigDecimal.ZERO) != 0) {
                operand1.divide(operand2, MathContext(32, RoundingMode.HALF_UP))
            } else {
                return "Error"
            }
            "x^y" -> operand1.pow(operand2?.toInt() ?: 1)

            // one argument operators
            "%" -> operand1.divide(BigDecimal(100))
            "sin" -> sin(operand1.toDouble()).toBigDecimal()
            "cos" -> cos(operand1.toDouble()).toBigDecimal()
            "tan" -> tan(operand1.toDouble()).toBigDecimal()
            "ln" -> ln(operand1.toDouble()).toBigDecimal()
            "log" -> log10(operand1.toDouble()).toBigDecimal()
            "√" -> sqrt(operand1.toDouble()).toBigDecimal()
            "x²" -> operand1.pow(2)

            else -> return "Error"
        }
    } catch (e: Exception) {
        return "Error"
    }

    // final result format
    return performCalculation(result.toPlainString(), null, "format", maxLength)
}
