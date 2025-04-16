package com.leehe228.eweek07a.example01.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(onNavigateA: () -> Unit, onNavigateB: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home Screen",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Button(onClick = {
            onNavigateA()
        }) {
            Text(text = "Screen A")
        }

        Button(onClick = {
            onNavigateB()
        }) {
            Text(text = "Screen B")
        }
    }
}
