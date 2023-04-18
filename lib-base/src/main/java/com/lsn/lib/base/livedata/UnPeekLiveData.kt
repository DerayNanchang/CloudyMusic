package com.lsn.lib.base.livedata


/**
 * TODO 唯一可信源设计
 * 我们在 V6 中继续沿用从 V3 版 "唯一可信源" 理念设计，
 * 确保 "事件" 发送权牢牢握在可信逻辑中枢手里，从而确保所有订阅者收到消息皆可靠且致，
 *
 *
 * 如这么说无体会，详见《吃透 LiveData 本质，享用可靠消息鉴权机制》解析：
 * https://xiaozhuanlan.com/topic/6017825943
 *
 *
 * TODO 提供 Builder 选项支持
 * 我们在 V6 中继续沿用 V3 版延 Builder 选项设计，
 * 目前提供 "是否允许发送 null" 选项。
 *
 *
 * Create by KunMinX at 2021/6/17
 */
open class UnPeekLiveData<T> : ProtectedUnPeekLiveData<T?> {
    constructor(value: T) : super(value) {}
    constructor() : super() {}

    public override fun setValue(value: T?) {
        super.setValue(value)
    }

    public override fun postValue(value: T?) {
        super.postValue(value)
    }


    class Builder<T> {
        /**
         * 是否允许传入 null value
         */
        private var isAllowNullValue = false
        fun setAllowNullValue(allowNullValue: Boolean): Builder<T> {
            isAllowNullValue = allowNullValue
            return this
        }

        fun create(): UnPeekLiveData<T> {
            val liveData: UnPeekLiveData<T> = UnPeekLiveData()
            liveData.isAllowNullValue = isAllowNullValue
            return liveData
        }
    }
}