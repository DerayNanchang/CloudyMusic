package com.lsn.lib.base.ui.activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.lsn.lib.base.R


/**
 * @Author : lsn
 * @CreateTime : 2023/4/12 下午 03:33
 * @Description :
 */
abstract class BaseNotificationActivity : BasePermissionActivity() {

//    var NOTIFICATION_O_ID = "app" //android 8.0 通知栏(APP 下载) 通道ID
//    var NOTIFICATION_O_NAME = "APP 下载" //android 8.0 通知栏(APP 下载) 通道 名称
//    var NOTIFICATION_O_MESSAGE = "message" //android 8.0 通知栏(APP 下载) 通道ID
//    var NOTIFICATION_O_NEW_MESSAGE = "首页消息通知" //android 8.0 通知栏(APP 下载) 通道 名称


    companion object {

        var NOTIFICATION_DOWNLOAD_ID = "app" //android 8.0 通知栏(APP 下载) 通道ID
        var NOTIFICATION_DOWNLOAD_NAME = "APP 下载" //android 8.0 通知栏(APP 下载) 通道 名称
        var NOTIFICATION_O_MESSAGE = "message" //android 8.0 通知栏(APP 下载) 通道ID
        var NOTIFICATION_O_NEW_MESSAGE = "首页消息通知" //android 8.0 通知栏(APP 下载) 通道 名称

    }


    var manager: NotificationManager? = null
    private var notifyBuilder: NotificationCompat.Builder? = null


    /**
     *  创建通知栏管理器
     */
    open fun createNotifyManager(channelMessage: String) {
        manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notifyBuilder = NotificationCompat.Builder(this, channelMessage)
    }

    /**
     *  创建通知栏渠道
     */
    open fun createNotifyChanel(notificationId: String, notificationName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId: String = notificationId
            val channelName: String = notificationName
            val importance = NotificationManager.IMPORTANCE_HIGH
            createNotificationChannel(false, channelId, channelName, importance)
        }
    }


    /**
     *  是否开启通知栏权限
     */
    open fun checkNotificationPermission( notificationId: String) {
        //得到NotificationManager的对象，用来实现发送Notification
        var notificationManagerObj = getSystemService(NOTIFICATION_SERVICE)
        notificationManagerObj?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // 打开 8.0 通知
                val notificationManager = notificationManagerObj as NotificationManager
                val channel: NotificationChannel? = notificationManager.getNotificationChannel(notificationId)
                if (channel != null){
                    if (channel.importance == NotificationManager.IMPORTANCE_NONE) {
                        val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
                        intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.id)
                        startActivity(intent)
                        Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun createNotificationChannel(
        isVibrate: Boolean,
        channelId: String,
        channelName: String,
        importance: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            //channel.setSound(null,null);
            if (isVibrate) {
                // 设置通知出现时的震动（如果 android 设备支持的话）
                channel.enableVibration(true)
                channel.vibrationPattern = longArrayOf(1500)
            } else {
                // 设置通知出现时不震动
                channel.enableVibration(false)
                channel.vibrationPattern = longArrayOf(0)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun closeNotify(title: String, content: String, pendingIntent: PendingIntent) {
        notifyBuilder?.apply {
            this.setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setShowWhen(false)

        }?.build()?.also {
            it.flags = it.flags or Notification.FLAG_AUTO_CANCEL
            manager?.notify(2, it)
        }
    }


    open fun startNotifyActivity(title: String, content: String, cls: Class<*>?) {
        val perIntent = Intent(this, cls)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, perIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        closeNotify(title, content, pendingIntent)
    }


    override fun onDestroy() {
        super.onDestroy()
        manager = null
        notifyBuilder = null
    }

}