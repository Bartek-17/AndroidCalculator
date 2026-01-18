package com.example.androidcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DisplayArea(display: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.End, // push the text to the right
        verticalArrangement = Arrangement.Bottom // align the text to the bottom
    ) {
        Text(text = display, style = MaterialTheme.typography.headlineLarge, maxLines = 1)
    }
}
