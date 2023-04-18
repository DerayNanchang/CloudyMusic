package com.lsn.comm.core.di

import com.lsn.comm.core.anotation.*
import com.lsn.comm.core.net.HttpClient
import com.lsn.lib.net.core.cache.CacheMode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetModule {


    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient.getInstance()
    }


    @Provides
    @ProvideOnlyNetwork
    fun provideOnlyNetwork(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.ONLY_NETWORK, HttpClient.NULL_CACHE)
    }

    @Provides
    @ProvideOnlyCache
    fun provideOnlyCache(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.ONLY_CACHE, HttpClient.NULL_CACHE)
    }


    @Provides
    @ProvideNetworkSuccessWriteCache1
    fun provideNetworkSuccessWriteCache1(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.NETWORK_SUCCESS_WRITE_CACHE, HttpClient.ONE_DAY)
    }

    @Provides
    @ProvideNetworkSuccessWriteCache3
    fun provideNetworkSuccessWriteCache3(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.NETWORK_SUCCESS_WRITE_CACHE, HttpClient.DAYS_3)
    }

    @Provides
    @ProvideNetworkSuccessWriteCache7
    fun provideNetworkSuccessWriteCache7(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.NETWORK_SUCCESS_WRITE_CACHE, HttpClient.DAYS_7)
    }

    @Provides
    @ProvideNetworkSuccessWriteCache30
    fun provideNetworkSuccessWriteCache30(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.NETWORK_SUCCESS_WRITE_CACHE, HttpClient.DAYS_30)
    }

    @Provides
    @ProvideReadCacheFailedRequestNetwork1
    fun provideReadCacheFailedRequestNetwork1(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.READ_CACHE_FAILED_REQUEST_NETWORK, HttpClient.ONE_DAY)
    }

    @Provides
    @ProvideReadCacheFailedRequestNetwork3
    fun provideReadCacheFailedRequestNetwork3(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.READ_CACHE_FAILED_REQUEST_NETWORK, HttpClient.DAYS_3)
    }

    @Provides
    @ProvideReadCacheFailedRequestNetwork7
    fun provideReadCacheFailedRequestNetwork7(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.READ_CACHE_FAILED_REQUEST_NETWORK, HttpClient.DAYS_7)
    }

    @Provides
    @ProvideReadCacheFailedRequestNetwork30
    fun provideReadCacheFailedRequestNetwork30(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.READ_CACHE_FAILED_REQUEST_NETWORK, HttpClient.DAYS_30)
    }

    @Provides
    @ProvideRequestNetworkFailedReadCache1
    fun provideRequestNetworkFailedReadCache1(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE, HttpClient.ONE_DAY)
    }

    @Provides
    @ProvideRequestNetworkFailedReadCache3
    fun provideRequestNetworkFailedReadCache3(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE, HttpClient.DAYS_3)
    }

    @Provides
    @ProvideRequestNetworkFailedReadCache7
    fun provideRequestNetworkFailedReadCache7(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE, HttpClient.DAYS_7)
    }

    @Provides
    @ProvideRequestNetworkFailedReadCache30
    fun provideRequestNetworkFailedReadCache30(): Retrofit {
        return HttpClient.getInstance()
            .provideRetrofit(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE, HttpClient.DAYS_30)
    }

}