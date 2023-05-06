package com.lsn.module.music.repository.net.impl

import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.net.flowTranData
import com.lsn.lib.ui.widget.banner.widget.banner.BannerItem
import com.lsn.module.music.MusicConstants
import com.lsn.module.music.MusicConstants.Companion.TOP_BALLAD
import com.lsn.module.music.MusicConstants.Companion.TOP_CL_ACG
import com.lsn.module.music.MusicConstants.Companion.TOP_CL_CA
import com.lsn.module.music.MusicConstants.Companion.TOP_CL_CLASSICAL
import com.lsn.module.music.MusicConstants.Companion.TOP_CL_DANYIN
import com.lsn.module.music.MusicConstants.Companion.TOP_CL_KR
import com.lsn.module.music.MusicConstants.Companion.TOP_CL_RAP
import com.lsn.module.music.MusicConstants.Companion.TOP_EQ_HOT
import com.lsn.module.music.MusicConstants.Companion.TOP_EQ_NEW
import com.lsn.module.music.MusicConstants.Companion.TOP_HOT
import com.lsn.module.music.MusicConstants.Companion.TOP_JP_ORICON
import com.lsn.module.music.MusicConstants.Companion.TOP_JP_TOP
import com.lsn.module.music.MusicConstants.Companion.TOP_KR_TOP
import com.lsn.module.music.MusicConstants.Companion.TOP_NET_TOP
import com.lsn.module.music.MusicConstants.Companion.TOP_NEW
import com.lsn.module.music.MusicConstants.Companion.TOP_ORIGINAL
import com.lsn.module.music.MusicConstants.Companion.TOP_SURGE
import com.lsn.module.music.entity.*
import com.lsn.module.music.net.client.MusicClient
import com.lsn.module.music.repository.net.i.IMusicRepository
import com.lsn.module.music.ui.activity.TopActivity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @Author : lsn
 * @CreateTime : 2023/4/24 下午 04:54
 * @Description :
 */
class MusicRepositoryImpl @Inject constructor(var musicClient: MusicClient) :
    IMusicRepository {

    override suspend fun getBanner(tag: String): Flow<ResponseEntity> {
        val banner = musicClient.getBanner()
        val bannerList = ArrayList<BannerItem>()
        banner.banners?.apply {
            this.forEach {
                val bannerItem = BannerItem()
                bannerItem.setImgUrl(it.imageUrl)
                bannerItem.setTitle(it.typeTitle)
                bannerItem.clientUrl = it.url
                bannerItem.titleColor = it.titleColor
                bannerList.add(bannerItem)
            }
        }
        return flowTranData(tag, bannerList)
    }

    override suspend fun getHitokotoEncode(tag: String): Flow<ResponseEntity> {
        return flowTranData(tag, musicClient.getHitokotoEncode())
    }

    override suspend fun getPersonalized(tag: String, limit: Int): Flow<ResponseEntity> {
        val personalized = musicClient.getPersonalized(limit).result
        personalized?.forEach {
            var value = ""
            if (it.playCount in 10000L..100000000L) {
                value = (it.playCount / 10000).toInt().toString() + "万"
            } else if (it.playCount > 100000000L) {
                value = (it.playCount / 100000000L).toInt().toString() + "亿"
            } else {
                value = it.playCount.toString()
            }
            it.playCountStr = "▷ $value"
        }
        return flowTranData(tag, personalized)
    }

    override suspend fun getRelatedPlaylist(tag: String): Flow<ResponseEntity> {
        val playlists = musicClient.getRelatedPlaylist().playlists
        val data = ArrayList<HomeSimpleItemData>()
        playlists?.forEach {
            val homeSimpleItemData = HomeSimpleItemData(
                it.id,
                it.coverImgUrl,
                it.name,
                it.creator.nickname,
                it.creator.userId
            )
            data.add(homeSimpleItemData)
        }
        return flowTranData(tag, data)
    }


    override suspend fun getAlbumNewest(tag: String): Flow<ResponseEntity> {
        val albums = musicClient.getAlbumNewest().albums
        val data = ArrayList<HomeSimpleItemData>()
        albums?.forEach {
            val homeSimpleItemData = HomeSimpleItemData(it.id, it.picUrl, it.name)
            data.add(homeSimpleItemData)
        }
        return flowTranData(tag, data)
    }

    override suspend fun getAlbumNew(tag: String): Flow<ResponseEntity> {
        val albums = musicClient.getAlbumNew().albums
        val data = ArrayList<HomeSimpleItemData>()
        albums?.forEach {
            val homeSimpleItemData = HomeSimpleItemData(it.id, it.picUrl, it.name)
            data.add(homeSimpleItemData)
        }
        return flowTranData(tag, data)
    }

    override suspend fun getMV(tag: String): Flow<ResponseEntity> {
        val albums = musicClient.getMV().data
        val data = ArrayList<HomeSimpleItemData>()
        albums?.forEach {
            val homeSimpleItemData = HomeSimpleItemData(it.id, it.cover, it.name)
            data.add(homeSimpleItemData)
        }
        return flowTranData(tag, data)
    }

    override suspend fun getArtists(tag: String): Flow<ResponseEntity> {
        val albums = musicClient.getArtists().artists
        val data = ArrayList<HomeSimpleItemData>()
        albums?.forEach {
            val homeSimpleItemData = HomeSimpleItemData(it.id, it.picUrl, it.name)
            data.add(homeSimpleItemData)
        }
        return flowTranData(tag, data)
    }

    override suspend fun getUserPlaylist(
        tag: String,
        userId: Long,
        limit: Int,
        offset: Int
    ): Flow<ResponseEntity> {
        val playlists = musicClient.getUserPlaylist(userId).playlist

        val data = ArrayList<Playlist>()
        playlists.forEach {

            var type: Int = 0
            if (it.creator.authenticationTypes == 2048) {
                type = 2
            } else {
                if (it.userId == userId) {
                    // 自己的歌单
                    type = 0
                } else {
                    // 添加别人的歌单
                    type = 1
                }
            }

            val playlist = Playlist(
                id = it.id,
                name = it.name,
                desc = it.description,
                picUrl = it.coverImgUrl,
                ownerId = it.creator.userId,
                ownerName = it.creator.nickname,
                trackCount = it.trackCount,
                playCount = it.playCount,
                type = type


            )

            println()
            data.add(playlist)
        }
        return flowTranData(tag, data)
    }

    override suspend fun getToplistDetail(tag: String): Flow<ResponseEntity> {


        val hotList = listOf(TOP_SURGE, TOP_NEW, TOP_ORIGINAL, TOP_HOT)
        val netList =
            listOf(TOP_CL_RAP, TOP_CL_CLASSICAL, TOP_CL_DANYIN, TOP_CL_ACG, TOP_CL_KR, TOP_CL_CA)
        val recommendList = listOf(
            TOP_NET_TOP,
            TOP_BALLAD,
            TOP_JP_ORICON,
            TOP_JP_TOP,
            TOP_EQ_HOT,
            TOP_EQ_NEW,
            TOP_KR_TOP
        )
        val hotListStr = hotList.toString()
        val netListStr = netList.toString()
        val recommendListStr = recommendList.toString()
        val list = musicClient.getToplistDetail().list
        val musicTopCurtData = ArrayList<MusicTopCurtData>()

        list.forEach {
            var type = 0
            if (hotListStr.contains(it.id.toString())) {
                type = 0
            } else if (netListStr.contains(it.id.toString())) {
                type = 1
            } else if (recommendListStr.contains(it.id.toString())) {
                type = 2
            }
            if (type == 0){
                MusicTopCurtData(
                    id = it.id,
                    name = it.name,
                    desc = it.description,
                    coverImgUrl = it.coverImgUrl,
                    updateFrequency = it.updateFrequency,
                    trackCount = it.trackCount,
                    playCount = it.playCount,
                    tracks = it.tracks,
                    type = type,
                    viewType = TopActivity.VIEW_TYPE_CONTENT_HOT
                )
            }else{
                MusicTopCurtData(
                    id = it.id,
                    name = it.name,
                    desc = it.description,
                    coverImgUrl = it.coverImgUrl,
                    updateFrequency = it.updateFrequency,
                    trackCount = it.trackCount,
                    playCount = it.playCount,
                    tracks = it.tracks,
                    type = type,
                    viewType = TopActivity.VIEW_TYPE_CONTENT_STANDARD
                )
            }
        }
        return flowTranData(tag, musicTopCurtData)
    }

    override suspend fun getPlaylistDetail(tag: String, id: Long): Flow<ResponseEntity> {

        val playlist = musicClient.getPlaylistDetail(id).playlist

        val tracksCurts = ArrayList<TracksCurt>()

        playlist.tracks?.forEach {

            var arNameStr = ""
            for (i in 0 until it.ar.size) {
                if (i == it.ar.size - 1) {
                    arNameStr += it.ar[i].name
                } else {
                    arNameStr += (it.ar[i].name + "/")
                }
            }

            var sq = false
            if (it.sq != null) {
                sq = true
            }

            val tracksCurt = TracksCurt(
                id = it.id,
                name = it.name,
                picUrl = it.al.picUrl,
                arName = arNameStr,// 歌手
                alName = it.al.name,// 专辑
                fee = it.fee,    // 是否免费
                sq = sq,
                mv = it.mv
            )

            tracksCurts.add(tracksCurt)
        }


        var value = ""
        if (playlist.playCount in 10000L..100000000L) {
            value = (playlist.playCount / 10000).toInt().toString() + "万"
        } else if (playlist.playCount > 100000000L) {
            value = (playlist.playCount / 100000000L).toInt().toString() + "亿"
        } else {
            value = playlist.playCount.toString()
        }
        value = "▷ $value"

        val musicPlaylistCurtRoot = MusicPlaylistCurtRoot(
            id = playlist.id,
            title = playlist.name,
            desc = playlist.description,
            playSize = playlist.playCount,
            playCountStr = value,
            coverImgUrl = playlist.coverImgUrl,
            userId = playlist.userId, //366231393
            createTime = playlist.createTime,
            updateTime = playlist.updateTime,
            subscribedCount = playlist.subscribedCount,
            cloudTrackCount = playlist.cloudTrackCount,
            tracksCurts = tracksCurts,
        )
        return flowTranData(tag, musicPlaylistCurtRoot)
    }

}