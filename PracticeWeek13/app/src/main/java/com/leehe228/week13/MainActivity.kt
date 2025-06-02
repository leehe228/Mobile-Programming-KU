package com.leehe228.week13

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.leehe228.week13.ui.theme.PracticeWeek13Theme

class MainActivity : ComponentActivity() {
    /* val br = BatteryBR()

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        this.registerReceiver(br, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        this.unregisterReceiver(br)
    } */

    fun handleIntent(intent: Intent) {
        val messageSender = intent.getStringExtra("messageSender")
        val messageBody = intent.getStringExtra("messageBody")

        setContent {
            PracticeWeek13Theme {
                MainScreen(messageSender, messageBody)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
    }
}
