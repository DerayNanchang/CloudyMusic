package com.lsn.lib.base.bus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lsn.lib.base.bus.LiveBus.ObserverWrapper


/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 上午 11:06
 * @Description :
 */
class LiveBus {
    private var bus: MutableMap<String, BusMutableLiveData<Any>> = HashMap()

    private constructor()

    companion object {
        fun get() = SingletonHolder.DEFAULT_BUS
    }


    private object SingletonHolder {
        val DEFAULT_BUS: LiveBus = LiveBus()
    }

//    fun get(): LiveBus = SingletonHolder.DEFAULT_BUS

    fun <T> with(key: String, type: Class<T>?): MutableLiveData<T> {
        if (!bus.containsKey(key)) {
            bus[key] = BusMutableLiveData()
        }
        return bus[key] as MutableLiveData<T>
    }

    fun with(key: String): MutableLiveData<Any> {
        return with(key, Any::class.java)
    }

    private class ObserverWrapper<T>(observer: Observer<T>?) :
        Observer<T?> {
        private val observer: Observer<T>?

        init {
            this.observer = observer
        }

        override fun onChanged(t: T?) {
            if (observer != null) {
                if (isCallOnObserve) {
                    return
                }
                observer.onChanged(t!!)
            }
        }

        private val isCallOnObserve: Boolean
            private get() {
                val stackTrace = Thread.currentThread().stackTrace
                if (stackTrace != null && stackTrace.size > 0) {
                    for (element in stackTrace) {
                        if (("android.arch.lifecycle.LiveData" == element.className) && "observeForever" == element.methodName) {
                            return true
                        }
                    }
                }
                return false
            }
    }

    private class BusMutableLiveData<T> : MutableLiveData<T>() {
        private val observerMap: MutableMap<Observer<*>, Observer<*>> = HashMap()
        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
            try {
                hook(observer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun observeForever(observer: Observer<in T>) {
            if (!observerMap.containsKey(observer)) {
                observerMap[observer] = ObserverWrapper(observer)
            }
            super.observeForever(observerMap[observer] as Observer<in T>)
        }

        override fun removeObserver(observer: Observer<in T>) {
            var realObserver: Observer<*>? = null
            realObserver = if (observerMap.containsKey(observer)) {
                observerMap.remove(observer)
            } else {
                observer
            }
            super.removeObserver(realObserver as Observer<in T>)
        }

        @Throws(Exception::class)
        private fun hook(observer: Observer<in T>) {
            //get wrapper's version
            val classLiveData = LiveData::class.java
            val fieldObservers = classLiveData.getDeclaredField("mObservers")
            fieldObservers.isAccessible = true
            val objectObservers = fieldObservers[this]
            val classObservers: Class<*> = objectObservers.javaClass
            val methodGet = classObservers.getDeclaredMethod("get", Any::class.java)
            methodGet.isAccessible = true
            val objectWrapperEntry = methodGet.invoke(objectObservers, observer)
            var objectWrapper: Any? = null
            if (objectWrapperEntry is Map.Entry<*, *>) {
                objectWrapper = objectWrapperEntry.value
            }
            if (objectWrapper == null) {
                throw NullPointerException("Wrapper can not be bull!")
            }
            val classObserverWrapper: Class<*> = objectWrapper.javaClass.superclass
            val fieldLastVersion = classObserverWrapper.getDeclaredField("mLastVersion")
            fieldLastVersion.isAccessible = true
            //get livedata's version
            val fieldVersion = classLiveData.getDeclaredField("mVersion")
            fieldVersion.isAccessible = true
            val objectVersion = fieldVersion[this]
            //set wrapper's version
            fieldLastVersion[objectWrapper] = objectVersion
        }
    }
}