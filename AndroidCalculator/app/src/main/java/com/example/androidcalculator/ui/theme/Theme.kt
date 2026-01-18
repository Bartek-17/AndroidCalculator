package com.example.androidcalculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkerBlue,
    secondary = LightBlue,
    background = DarkBlueBackground,
    surface = DarkBlueBackground
)

private val LightColorScheme = lightColorScheme(
    primary = DarkerBlue,
    secondary = LightBlue,
    background = DarkBlueBackground,
    surface = DarkBlueBackground
)

@Composable
fun CalculatorTheme(
    darkTheme: Boolean = true, // Always use dark theme
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
