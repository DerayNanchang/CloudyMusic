package com.lsn.module.music.repository.net.impl

import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.net.flowTranData
import com.lsn.lib.ui.widget.banner.widget.banner.BannerItem
import com.lsn.module.music.entity.HomeSimpleItemData
import com.lsn.module.music.entity.MusicPlaylistCurtRoot
import com.lsn.module.music.entity.Playlist
import com.lsn.module.music.entity.TracksCurt
import com.lsn.module.music.net.client.MusicClient
import com.lsn.module.music.repository.net.i.IMusicRepository
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


        val musicPlaylistCurtRoot = MusicPlaylistCurtRoot(
            id = playlist.id,
            title = playlist.name,
            desc = playlist.description,
            playSize = playlist.playCount,
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