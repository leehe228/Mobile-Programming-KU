package com.leehe228.week13

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ClipData.newIntent
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.service.autofill.Validators.or
import android.widget.Toast

class SmsBR : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val message = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            val messageSender = message[0].originatingAddress
            val messageBody = message.joinToString(separator = "") { it.messageBody }

            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("messageSender", messageSender)
            intent.putExtra("messageBody", messageBody)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            val pendingIntent = PendingIntent.getActivity(
                context,
                100,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            context?.let {
                val application = context.applicationContext as MyApplication

                if (application.isForeground) {
                    // foreground -> start activity
                    context.startActivity(intent)
                } else {
                    // background -> make notification
                    makeNotification(context, "$messageSender : $messageBody", pendingIntent)
                }
            }

            // Toast.makeText(context, "$messageSender : $messageBody", Toast.LENGTH_SHORT).show()
        }
    }
}
