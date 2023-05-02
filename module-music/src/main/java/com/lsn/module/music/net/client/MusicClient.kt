package com.lsn.module.music.net.client

import com.lsn.comm.core.anotation.ProvideReadCacheFailedRequestNetwork30
import com.lsn.comm.core.net.HttpClient
import com.lsn.lib.net.core.ResponseModel
import com.lsn.lib.net.core.cache.CacheMode
import com.lsn.module.music.entity.*
import com.lsn.module.music.net.service.IMusicService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:57
 * @Description :
 */
class MusicClient @Inject constructor(
    @ProvideReadCacheFailedRequestNetwork30 var requestNetwork30: Retrofit,
    var retrofit: HttpClient,
) {


    suspend fun getBanner(): MusicBannerRoot {
        return getNeteaseNetConfig().create(IMusicService::class.java)
            .getBanner()
    }

    suspend fun getPersonalized(limit: Int): MusicPersonalizedRoot {
        return getNeteaseNetConfig().create(IMusicService::class.java)
            .getPersonalized(limit)
    }



    suspend fun getRelatedPlaylist(): MusicRelatedPlaylistRoot {
        return getNeteaseNetConfig().create(IMusicService::class.java)
            .getRelatedPlaylist()
    }



    suspend fun getAlbumNewest(): MusicAlbumNewRoot {
        return getNeteaseNetConfig().create(IMusicService::class.java)
            .getAlbumNewest()
    }

    suspend fun getAlbumNew(): MusicAlbumNewRoot {
        return getNeteaseNetConfig().create(IMusicService::class.java)
            .getAlbumNew()
    }

    suspend fun getMV(): MusicMVRoot {
        return getNeteaseNetConfig().create(IMusicService::class.java)
            .getMV()
    }

    suspend fun getArtists(): MusicArtistsRoot {
        return getNeteaseNetConfig().create(IMusicService::class.java)
            .getArtists()
    }


    suspend fun getUserPlaylist(): MusicPlaylistRoot {
        return getNeteaseNetConfig().create(IMusicService::class.java)
            .getUserPlaylist()
    }

    suspend fun getPlaylistDetail(id:Long): MusicPlaylistDetailRoot {
        return getNeteaseNetConfig().create(IMusicService::class.java)
            .getPlaylistDetail(id)
    }


    private fun getNeteaseNetConfig(): Retrofit {
        val linkUrl = retrofit.getLinkUrl()
        retrofit.setCacheModel(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE)
        retrofit.setCacheTime(HttpClient.DAYS_30)
        val build = retrofit.getBuildRetrofit()
            .client(retrofit.getOkHttpClient(ResponseModel.NETEASECLOUD))
            .baseUrl(linkUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return build
    }

}