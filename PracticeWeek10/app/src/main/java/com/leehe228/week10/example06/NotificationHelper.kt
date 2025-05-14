package com.leehe228.week10.example06

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.leehe228.week10.R

fun createPendingIntent(context: Context, msg: String): PendingIntent {
//    val intent = Intent(Intent.ACTION_VIEW, "myapp://greenjoahome.com/$msg".toUri()).apply {
//        setPackage(context.packageName)
//        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//    }

    val intent = Intent(Intent.ACTION_VIEW, "myapp://greenjoahome.com?msg=$msg".toUri()).apply {
        setPackage(context.packageName)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }

    val pendingIntent =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.getActivity(
            context, 123, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        else PendingIntent.getActivity(
            context, 123, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
    return pendingIntent
}

object NotificationHelper {
    const val CHANNEL_ID = "MyChannel"
    const val CHANNEL_NAME = "TimeCheckChannel"
    const val IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT
    const val NOTIFICATION_ID = 0

    fun initChannel(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (manager.getNotificationChannel(CHANNEL_ID) == null) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, IMPORTANCE).apply {
                description = "일정 알림 채널"
            }
            manager.createNotificationChannel(channel)
        }
    }

    fun notify(context: Context, msg: String) {
        val pendingIntent = createPendingIntent(context, msg)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_access_alarm_24).setContentTitle("일정 알림")
            .setContentText(msg).setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent).setAutoCancel(true).build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }
}
