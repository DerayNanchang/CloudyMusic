package com.lsn.comm.core.anotation

import javax.inject.Qualifier

/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 06:31
 * @Description :
 */

/**
 * 仅请求网络，默认模式  （不写缓存）
 * @see  com.lsn.lib.net.core.cache.CacheMode
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideOnlyNetwork()

/**
 * 仅读取缓存  （不写缓存）
 * @see  com.lsn.lib.net.core.cache.CacheMode
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideOnlyCache()


/**
 * 请求成功后，写入缓存 跟[.ONLY_NETWORK] 默认模式相比，仅多了写缓存的操作
 * @see  com.lsn.lib.net.core.cache.CacheMode
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideNetworkSuccessWriteCache1()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideNetworkSuccessWriteCache3()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideNetworkSuccessWriteCache7()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideNetworkSuccessWriteCache30()

/**
 * 先读取缓存，失败后再请求网络  (网络请求成功，写缓存)
 * @see  com.lsn.lib.net.core.cache.CacheMode
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideReadCacheFailedRequestNetwork1()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideReadCacheFailedRequestNetwork3()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideReadCacheFailedRequestNetwork7()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideReadCacheFailedRequestNetwork30()

/**
 * 先请求网络，失败后再读取缓存  (网络请求成功，写缓存)
 * @see  com.lsn.lib.net.core.cache.CacheMode#ONLY_NETWORK
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideRequestNetworkFailedReadCache1()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideRequestNetworkFailedReadCache3()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideRequestNetworkFailedReadCache7()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvideRequestNetworkFailedReadCache30()
