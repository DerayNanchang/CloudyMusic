package com.lsn.module.music.net.service

import com.lsn.module.music.entity.*
import com.lsn.module.provider.comm.api.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:55
 * @Description :
 */
interface IMusicService {


    @GET(ApiConstants.Music.BANNER)
    suspend fun getBanner(): MusicBannerRoot


    // 推荐歌单 personalized
    @GET(ApiConstants.Music.PERSONALIZED)
    suspend fun getPersonalized(@Query("limit") limit: Int): MusicPersonalizedRoot


    // 关联歌单
    @GET(ApiConstants.Music.RELATED_PLAYLIST)
    suspend fun getRelatedPlaylist(): MusicRelatedPlaylistRoot


    @GET(ApiConstants.Music.ALBUM_NEWEST)
    suspend fun getAlbumNewest(): MusicAlbumNewRoot

    // 最新新碟
    @GET(ApiConstants.Music.ALBUM_NEW)
    suspend fun getAlbumNew(): MusicAlbumNewRoot

    // 最新 mv
    @GET(ApiConstants.Music.MV_FIRST)
    suspend fun getMV(): MusicMVRoot

    // 热门歌手
    @GET(ApiConstants.Music.TOP_ARTISTS)
    suspend fun getArtists(): MusicArtistsRoot


    // 用户歌单
    @GET(ApiConstants.Music.USER_PLAYLIST)
    suspend fun getUserPlaylist(): MusicPlaylistRoot


    // 歌单详情
    @GET(ApiConstants.Music.PLAYLIST_DETAIL)
    suspend fun getPlaylistDetail(@Query("id") id: Long): MusicPlaylistDetailRoot


}