package com.example.androidcalculator

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun StartScreen(navController: NavController) {
    val activity = LocalActivity.current
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val buttonModifier = Modifier.size(width = 200.dp, height = 80.dp)

            Button(
                onClick = { navController.navigate("simple_calculator") },
                shape = RoundedCornerShape(8.dp),
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text("Simple Calculator")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("advanced_calculator") },
                shape = RoundedCornerShape(8.dp),
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text("Advanced Calculator")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("app_info") },
                shape = RoundedCornerShape(8.dp),
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text("App Information")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { activity?.finish() },
                shape = RoundedCornerShape(8.dp),
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
            ) {
                Text("Exit")
            }
        }
    }
}
