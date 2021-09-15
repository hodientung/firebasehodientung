package gst.training.practicefirebase.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import gst.training.practicefirebase.MainActivity
import gst.training.practicefirebase.R
import gst.training.practicefirebase.utils.Constants

// f00aklZPS9irQ3gUIzpACA:APA91bGJVAks3VlkdKzSz3hgOMj6JOE71AZm5WWPrK8QKoO3PuZeXQINZU29Z7OZ3s6UuluHyLPIFXFWpNP3S3b7Vbg9_ZW3tLQn1RPekTZA9oyt1m0r5RAVZBdk0VXLqN3IDPltV5uL
class TestFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val receiveData: Map<String, String> = remoteMessage.data
        val title = receiveData["user_name"]
        val body = receiveData[("test_notification")]
        sendNotification(title, body)
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

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("Test: ", token)
    }
}