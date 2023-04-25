package com.lsn.comm.core.net

import com.google.gson.Gson
import com.lsn.comm.core.app.BaseCoreApplication.Companion.app
import com.lsn.lib.net.core.cache.CacheManager
import com.lsn.lib.net.core.cache.CacheMode
import com.lsn.lib.net.core.cache.IFileCache
import com.lsn.lib.net.core.interceptors.CacheInterceptor
import com.lsn.lib.net.core.interceptors.ResponseParseInterceptor
import com.lsn.lib.utils.util.DateUtil
import com.lsn.lib.utils.util.SizeUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/3/9 下午 02:40
 * @Description :
 */
class HttpClient @Inject constructor() {


    private var config = app.netConfigEntity


    private var cacheManager = CacheManager(
        File(config.cacheFilePath, config.diskCacheName),
        SizeUtils.mb2bit(config.diskCacheSize)
    )
    private var gson = Gson()
    private var cacheTime = 0L // 默认不缓存
    private var cacheMode = CacheMode.ONLY_NETWORK // 默认不缓存
    private var singleRetrofit = Retrofit.Builder()

    companion object {
        fun getInstance() = HttpClickHelp.httpClient
        var ONE_DAY = 24 * 60 * 60 * 1000L
        var DAYS_3 = 3 * ONE_DAY
        var DAYS_7 = 7 * ONE_DAY
        var DAYS_30 = 30 * ONE_DAY
        const val NULL_DATA = -1000
        const val NULL_CACHE = -2L
    }


    private object HttpClickHelp {
        val httpClient = HttpClient()
    }

    fun setCacheModel(cacheMode: CacheMode): HttpClient {
        this.cacheMode = cacheMode
        return this
    }

    fun setCacheTime(cacheTime: Long): HttpClient {
        this.cacheTime = cacheTime
        return this
    }

    fun getBaseUrl(): String {
        return config.apiServiceUrl
    }

    fun provideRetrofit(cacheMode: CacheMode, cacheTime: Long): Retrofit {
        if (cacheMode != CacheMode.ONLY_NETWORK) {
            return setCacheModel(cacheMode).provideRetrofit()
        } else {
            return setCacheModel(cacheMode)
                .setCacheTime(cacheTime)
                .provideRetrofit()
        }
    }


    private fun provideRetrofit(): Retrofit {
        var baseUrl = getLinkUrl()
        return getBuildRetrofit()
            .client(getOkHttpClient())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun getBuildRetrofit(isSingle: Boolean = false): Retrofit.Builder {
        return if (isSingle) {
            Retrofit.Builder()
        } else {
            singleRetrofit
        }
    }

    fun getOkHttpClient(isStandard: Boolean = true): OkHttpClient {

        return OkHttpClient.Builder().apply {
            addInterceptor(ResponseParseInterceptor(gson,isStandard))
            addInterceptor(CacheInterceptor(getInternalCache(), gson, cacheMode, cacheTime))

            /* if (isSSL) {
                 this.sslSocketFactory(TrustAllCerts.createSSLSocketFactory())
                 this.hostnameVerifier(TrustAllCerts.TrustAllHostnameVerifier())
             }*/

            connectTimeout(config.connectTime, TimeUnit.SECONDS)
            readTimeout(config.readTime, TimeUnit.SECONDS)
            writeTimeout(config.writeTime, TimeUnit.SECONDS)
        }.build()
    }


    private fun getInternalCache(): IFileCache {
        return cacheManager.internalCache
    }

    fun getGson(): Gson {
        return gson
    }


    fun getLinkUrl(baseUrl: String = config.apiServiceUrl): String {
        return baseUrl + config.bridgeName
    }


    private fun provideHttpClient(): HttpClient {
        return provideHttpClient(CacheMode.ONLY_NETWORK, 0L)
    }

    private fun provideHttpClient(cacheModel: CacheMode): HttpClient {
        return provideHttpClient(cacheModel, DateUtil.formatMill(config.defCacheDay))
    }


    private fun provideHttpClient(cacheModel: CacheMode, cacheTime: Long): HttpClient {
        return setBaseHttpClient(cacheModel, cacheTime)
    }

    private fun setBaseHttpClient(
        cacheModel: CacheMode,
        cacheTime: Long
    ): HttpClient {
        return getInstance()
            .setCacheModel(cacheModel)
            .setCacheTime(cacheTime)
    }

}