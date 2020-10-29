package com.android.xknowledge.sdk.ui.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

/**
 * 通知相关
 */
class NotificationActivity : TitleActivity() {
    var NOTIFICATION_ID = 234
    var CHANNEL_NAME = "234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val sendButton = findViewById<Button>(R.id.notification_button_send)
        sendButton.setOnClickListener {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_NAME, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(this, CHANNEL_NAME)
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle("John")
                .setContentText("Hey let's meet at Sophie's house tonight.It's at 797 $ King St.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAllowSystemGeneratedContextualActions(true)

            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(NOTIFICATION_ID, builder.build())
            }
        }
    }
}