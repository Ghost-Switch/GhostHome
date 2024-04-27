package com.example.ghosthome.service

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat


class TimerService : Service() {
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var sharedPreferences: SharedPreferences
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences("TimerPrefs", MODE_PRIVATE)
        startForeground()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAGO","SERVICE STARED")

        val remainingMillis = sharedPreferences.getLong("millisLeft", 0)

        if (remainingMillis > 0) {
            startTimer(remainingMillis)
        }
//        return super.onStartCommand(intent, flags, startId)
        // Create notification
//        val notificationIntent = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(
//            this,
//            0,
//            notificationIntent,
//            0
//        )
//
//        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle("Timer Service")
//            .setContentText("Timer is running...")
//            .setSmallIcon(androidx.core.R.drawable.notification_bg)
//            .setContentIntent(pendingIntent)
//            .build()
//
//        startForeground(NOTIFICATION_ID, notification)

        return START_STICKY
    }

    private fun startTimer(milliseconds: Long) {
        countDownTimer = object : CountDownTimer(milliseconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val sharedPreferencesEditor = sharedPreferences.edit()
                sharedPreferencesEditor.putLong("millisLeft", millisUntilFinished)
                sharedPreferencesEditor.apply()
                val savedMillis = sharedPreferences.getLong("millisLeft", 0)
                Log.d("TAG","SERVICE STARED"+savedMillis)
            }
            override fun onFinish() {
                val sharedPreferencesEditor = sharedPreferences.edit()
                sharedPreferencesEditor.remove("millisLeft")
                sharedPreferencesEditor.apply()
            }
        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
    }
    private fun startForeground() {
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("my_service", "My Background Service")
            } else {
                // If earlier version channel ID is not used
                // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
                ""
            }

        val notificationBuilder = NotificationCompat.Builder(this, channelId )
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.mipmap.sym_def_app_icon)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(101, notification)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String{
        val chan = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}