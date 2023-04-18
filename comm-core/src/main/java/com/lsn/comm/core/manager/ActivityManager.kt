package com.lsn.comm.core.manager

import android.app.Activity
import android.app.PendingIntent
import android.os.Process
import java.util.*


/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 下午 01:37
 * @Description :
 */
class ActivityManager {

    private constructor()

    private var activityStack: Stack<Activity?>? = null
    private var instance: ActivityManager? = null
    private val restartIntent: PendingIntent? = null

    companion object {
        fun get() = SingletonHolder.INSTANCE
    }

    private object SingletonHolder {
        val INSTANCE: ActivityManager = ActivityManager()
    }

    /**
     * 单一实例
     */
    fun get(): ActivityManager? {
        if (instance == null) {
            instance = ActivityManager()
        }
        return instance
    }

    fun current(): Activity? {
        return if (activityStack!!.size > 0) activityStack!![0] else null
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity?) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    fun removeActivity(activity: Activity?) {
        if (activityStack != null) {
            activityStack!!.remove(activity)
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return activityStack!!.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack!!.remove(activity)
            activity.finish()
            activity = null
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity!!.javaClass == cls) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i]!!.finish()
            }
            i++
        }
        activityStack!!.clear()
    }

    /**
     * 退出应用程序
     */
    fun exitApp() {
        try {
            finishAllActivity()
            System.exit(0)
            Process.killProcess(Process.myPid())
        } catch (e: Exception) {
        }
    }

}