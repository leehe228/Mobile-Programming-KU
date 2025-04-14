package com.leehe228.eweek05a.uiexamples

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun RandomColorButton() {
    val scope = rememberCoroutineScope() // scope instance
    var color by remember { mutableStateOf(Color.Red) } // state variable

    Column {
        Button(
            onClick = {
                scope.launch { // start coroutine
                    while (true) {
                        delay(500) // 500ms sleep <- suspend function
                        color = Color(
                            Random.nextInt(0xFF),
                            Random.nextInt(0xFF),
                            Random.nextInt(0xFF),
                            Random.nextInt(0xFF)
                        )
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(color)
        ) {
            Text("Change Color")
        }
    }
}

@Composable
fun RandomColorButton2() {
    val scope = rememberCoroutineScope()
    var color by remember { mutableStateOf(Color.Red) }

    Column {
        Button(
            onClick = {
                val job = scope.launch { // start coroutine, and get job instance (to control)
                    while (true) {
                        delay(500)
                        color = Color(
                            Random.nextInt(0xFF),
                            Random.nextInt(0xFF),
                            Random.nextInt(0xFF),
                            Random.nextInt(0xFF)
                        )
                    }
                }

                scope.launch { // start coroutine
                    delay(2000)
                    job.cancel() // cancel coroutine
                }
            },
            colors = ButtonDefaults.buttonColors(color)
        ) {
            Text("Change Color")
        }
    }
}

@Preview
@Composable
private fun RandomColorButtonPreview() {
    Column {
        RandomColorButton()
        RandomColorButton2()
    }

}
