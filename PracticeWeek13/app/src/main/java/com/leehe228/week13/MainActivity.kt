package com.leehe228.week13

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            PracticeWeek13Theme {
                MainScreen()
            }
        }
    }
}
