package com.example.dietproapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService()  {
    override fun onNewToken(tok: String) {
        super.onNewToken(tok)
    }

    val TAG = "MyFirebaseMessaging"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body} - ${it.title}")
            sendNotification(it.title.toString(), it.body.toString())
        }

    }

    private fun sendNotification(title: String, messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val requestCode = 0
        val pendingIntent = PendingIntent.getActivity(
            this,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        val channelId = "fcm_default_channel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT,
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notificationId = 0
        notificationManager.notify(notificationId, notificationBuilder.build())
    }


//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//
//        Log.d(TAG, "Pesan FCM diterima.")
//
//        if (remoteMessage.notification != null) {
//
//            Log.d(TAG, "Judul Notifikasi: ${remoteMessage.notification!!.title}")
//            Log.d(TAG, "Isi Notifikasi: ${remoteMessage.notification!!.body}")
//
//            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
//        }
//
//    }

//    @SuppressLint("RemoteViewLayout")
//    fun getRemoteView(title: String, message: String):  RemoteViews{
//        val remoteView = RemoteViews("com.example.dietproapp", R.layout.notification)
//
//        remoteView.setTextViewText(R.id.title, title)
//        remoteView.setTextViewText(R.id.message, message)
//        remoteView.setImageViewResource(R.id.appLogo, R.drawable.dietpro)
//
//        return remoteView
//    }
//    private fun generateNotification(title: String, message: String) {
//
//        Log.d(TAG, "Membuat dan menampilkan notifikasi.")
//
//        val intent = Intent(this,NotifikasiActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingIntent   =   PendingIntent.getActivity(this, 0, intent,
//            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
//
//        //channel id, channel name
//        var builder: NotificationCompat.Builder =   NotificationCompat.Builder(applicationContext, channelId)
//            .setSmallIcon(R.drawable.dietpro)
//            .setAutoCancel(true)
//            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
//            .setOnlyAlertOnce(true)
//            .setContentIntent(pendingIntent)
//
//        builder =   builder.setContent(getRemoteView(title, message))
//
//
//        val notificationManager =   getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            val notificationChannel =   NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(notificationChannel)
//
//        }
//
//        notificationManager.notify(0,builder.build())
//
//    }

}