package gst.training.practicefirebase.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import gst.training.practicefirebase.MainActivity
import gst.training.practicefirebase.R
import gst.training.practicefirebase.utils.Constants

class TestFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val notification: RemoteMessage.Notification? = p0.notification
        if (notification == null) {
            return
        } else {
            val strTitle = notification.title
            val strMessage = notification.body
            sendNotification(strTitle, strMessage)
        }

    }

    private fun sendNotification(strTitle: String?, strMessage: String?) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationBuilder = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setContentTitle(strTitle)
            .setContentText(strMessage)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
        val notification = notificationBuilder.build()
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)

    }

}