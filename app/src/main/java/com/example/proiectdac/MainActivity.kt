package com.example.proiectdac

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.security_page_1.*


class MainActivity : AppCompatActivity() {
    //notification
    private val CHANNEL_ID = "channel_id-example_01"
    private val notificationid = 101
    //function to send notification
    var isRecursionEnable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.security_page_1)
        //we need some safety measure for our mobile banking app
        //so our app will ask for a PIN code which our client will set.
        //but for this, we need to work with files, like some database
        //and some algorithm for crypting that code, safety reasons
        createNotificationChannel()
        //notification
        //spamNotification()
        Thread {
            Thread.sleep(1000)
            sendNotification()
        }.start()
        //button 1
        btnAmCont.setOnClickListener {
            val intent = Intent(this, ExistingAccountHandler::class.java)
            startActivity(intent)
        }
            //button 2
            btnNuAmCont.setOnClickListener {
                val intent2 = Intent(this, NewAccountHandler::class.java)
                startActivity(intent2);
            }
        }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notificatio Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply{
                description = descriptionText
            }
            val notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun sendNotification()
    {
        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon_1_foreground)
            .setContentTitle("Yooo..")
            .setContentText("Just a friendly nudge to remind you that there's money in your account")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationid, builder.build())
        }
    }
    private fun spamNotification()
        {
            Thread.sleep(10000)
        sendNotification()
    }
    }
