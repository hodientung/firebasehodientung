package gst.training.practicefirebase

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import gst.training.practicefirebase.utils.Constants


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createChannelNotification()
    }

    private fun createChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.CHANNEL_ID,
                "PushNotification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}