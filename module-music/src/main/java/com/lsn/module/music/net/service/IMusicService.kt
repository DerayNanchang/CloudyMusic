package com.lsn.module.music.net.service

import com.lsn.module.music.entity.*
import com.lsn.module.provider.comm.api.ApiConstants
import retrofit2.http.GET


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:55
 * @Description :
 */
interface IMusicService {


    @GET(ApiConstants.Music.BANNER)
    suspend fun getBanner(): MusicBannerList

    // 最近专辑
    @GET(ApiConstants.Music.ALBUM_NEWEST)
    suspend fun getAlbumNewest(): MusicAlbumNew

    // 最新新碟
    @GET(ApiConstants.Music.ALBUM_NEW)
    suspend fun getAlbumNew(): MusicAlbumNew

    // 最新 mv
    @GET(ApiConstants.Music.MV_FIRST)
    suspend fun getMV(): MusicMVRoot

    // 热门歌手
    @GET(ApiConstants.Music.TOP_ARTISTS)
    suspend fun getArtists(): MusicArtistsRoot



}