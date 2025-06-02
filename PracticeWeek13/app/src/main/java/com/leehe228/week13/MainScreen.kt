package com.leehe228.week13

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    messageSender: String?,
    messageBody: String?,
    modifier: Modifier = Modifier
) {
    val permissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.RECEIVE_SMS,
            android.Manifest.permission.POST_NOTIFICATIONS
        )
    )

    LaunchedEffect(Unit) {
        permissions.launchMultiplePermissionRequest()
    }

    var message by remember { mutableStateOf("") }

    LaunchedEffect(messageBody) {
        if (messageBody != null && permissions.permissions[0].status.isGranted) {
            message = "$messageSender : $messageBody"
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Received Message",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = message,
            fontSize = 16.sp
        )
    }

    /* var msg by remember { mutableStateOf("") }

    MyBR(brAction = Intent.ACTION_POWER_CONNECTED) {
        msg = "power connected"
    }

    MyBR(brAction = Intent.ACTION_POWER_DISCONNECTED) {
        msg = "power disconnected"
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = msg,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    } */
}
