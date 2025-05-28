package com.leehe228.week13

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun MyBR(
    brAction: String,
    brEvent: (intent: Intent?) -> Unit
) {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val br = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                brEvent(intent)
            }
        }
        val intentFilter = IntentFilter(brAction)
        context.registerReceiver(br, intentFilter)

        onDispose {
            context.unregisterReceiver(br)
        }
    }
}
