package com.lsn.module.music.repository.net.impl

import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.net.flowTranData
import com.lsn.lib.ui.widget.banner.widget.banner.BannerItem
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
import com.lsn.module.music.exts.topListDetailContentData
import com.lsn.module.music.net.client.MusicClient
import com.lsn.module.music.repository.net.i.IMusicRepository
import com.lsn.module.music.ui.activity.TopActivity.Companion.VIEW_TYPE_CONTENT_HOT
import com.lsn.module.music.ui.activity.TopActivity.Companion.VIEW_TYPE_CONTENT_STANDARD
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
        val data = ArrayList<BannerItem>()
        musicClient.getBanner().banners?.forEach {
            val bannerItem = BannerItem()
            bannerItem.setImgUrl(it.imageUrl)
            bannerItem.setTitle(it.typeTitle)
            bannerItem.clientUrl = it.url
            bannerItem.titleColor = it.titleColor
            data.add(bannerItem)
        }
        return flowTranData(tag, data)
    }

    override suspend fun getHitokotoEncode(tag: String): Flow<ResponseEntity> {
        return flowTranData(tag, musicClient.getHitokotoEncode())
    }

    override suspend fun getPersonalized(tag: String, limit: Int): Flow<ResponseEntity> {
        val personalized = musicClient.getPersonalized(limit).result
        val data = ArrayList<MusicPersonalized>()
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
            data.add(it)
        }
        return flowTranData(tag, data)
    }

    override suspend fun getRelatedPlaylist(tag: String): Flow<ResponseEntity> {
        val playlists = musicClient.getRelatedPlaylist().playlists
        val data = playlists.map {
            val homeSimpleItemData = HomeSimpleItemData(
                it.id,
                it.coverImgUrl,
                it.name,
                it.creator.nickname,
                it.creator.userId
            )
        }
        return flowTranData(tag, data)
    }


    override suspend fun getAlbumNewest(tag: String): Flow<ResponseEntity> {
        val data = ArrayList<HomeSimpleItemData>()
        musicClient.getAlbumNewest().albums?.forEach {
            data.add(HomeSimpleItemData(it.id, it.picUrl, it.name))
        }
        return flowTranData(tag, data)
    }

    override suspend fun getAlbumNew(tag: String): Flow<ResponseEntity> {
        val data = ArrayList<HomeSimpleItemData>()
        musicClient.getAlbumNew().albums?.forEach {
            data.add(HomeSimpleItemData(it.id, it.picUrl, it.name))
        }
        return flowTranData(tag, data)
    }

    override suspend fun getMV(tag: String): Flow<ResponseEntity> {
        val data = ArrayList<HomeSimpleItemData>()
        musicClient.getMV().data?.forEach {
            data.add(HomeSimpleItemData(it.id, it.cover, it.name))
        }
        return flowTranData(tag, data)
    }

    override suspend fun getArtists(tag: String): Flow<ResponseEntity> {
        val albums = musicClient.getArtists().artists
        val data = albums.map {
            HomeSimpleItemData(it.id, it.picUrl, it.name)
        }
        return flowTranData(tag, data)
    }

    override suspend fun getUserPlaylist(
        tag: String,
        userId: Long,
        limit: Int,
        offset: Int
    ): Flow<ResponseEntity> {

        val data = musicClient.getUserPlaylist(userId).playlist.map {
            var type: Int = if (it.creator.authenticationTypes == 2048) {
                2
            } else {
                if (it.userId == userId) {
                    // 自己的歌单
                    0
                } else {
                    // 添加别人的歌单
                    1
                }
            }
            var value = ""
            if (it.playCount in 10000L..100000000L) {
                value = (it.playCount / 10000).toInt().toString() + "万"
            } else if (it.playCount > 100000000L) {
                value = (it.playCount / 100000000L).toInt().toString() + "亿"
            } else {
                value = it.playCount.toString()
            }
            var valueStr = "▷ $value"

            DecUserPlaylist(
                type = type,
                id = it.id,
                title = it.name,
                desc = it.description,
                playSize = it.playCount,
                playCountStr = valueStr,
                coverImgUrl = it.coverImgUrl,
                userId = it.userId,
                createTime = it.createTime,
                updateTime = it.updateTime,
                subscribedCount = it.subscribedCount,
                cloudTrackCount = it.cloudTrackCount,
            )
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
        var title = ""
        var content = ""
        val data = HashMap<String, List<MusicTopCurtData>>()
        val hotDataList = ArrayList<MusicTopCurtData>()
        val netDataList = ArrayList<MusicTopCurtData>()
        val recommendDataList = ArrayList<MusicTopCurtData>()
        list.forEach {
            var type = 0
            var viewType = 0
            if (hotListStr.contains(it.id.toString())) {
                type = 0
                viewType = VIEW_TYPE_CONTENT_HOT
                hotDataList.add(topListDetailContentData(it, type, viewType))
            } else if (netListStr.contains(it.id.toString())) {
                type = 1
                viewType = VIEW_TYPE_CONTENT_STANDARD
                netDataList.add(topListDetailContentData(it, type, viewType))
            } else if (recommendListStr.contains(it.id.toString())) {
                type = 2
                viewType = VIEW_TYPE_CONTENT_STANDARD
                recommendDataList.add(topListDetailContentData(it, type, viewType))
            }
        }
        data["官方榜"] = hotDataList
        data["曲风榜"] = netDataList
        data["特色榜"] = recommendDataList
        return flowTranData(tag, data)
    }

    override suspend fun getPlaylistDetail(tag: String, id: Long): Flow<ResponseEntity> {

        val playlist = musicClient.getPlaylistDetail(id).playlist
        val standardMusicList = ArrayList<StandardMusic>()
        playlist.tracks?.forEach {
            var arNameStr = ""
            val arList = ArrayList<StandardAR>()
            for (i in 0 until it.ar.size) {
                if (i == it.ar.size - 1) {
                    arNameStr += it.ar[i].name
                } else {
                    arNameStr += (it.ar[i].name + "/")
                }
                arList.add(StandardAR(it.ar[i].id, it.ar[i].name))
            }
            var sq = false
            if (it.sq != null) {
                sq = true
            }
            // 无损 sq 32000 ，极高 h 25600 , 较高 192000 m ,标准 0 128000 l  高保帧 hr 1676254
            val netInfo = NetInfo(it.fee, maxbr = 32000)
            val al = StandardAl(it.al.id, it.al.name, it.al.picUrl)
            val standardMusic = StandardMusic(
                SourceKind.NET,
                it.id,
                it.name,
                it.al.picUrl,
                arList,
                arNameStr,
                al,
                netInfo,
                null,
            )
            standardMusicList.add(standardMusic)
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

        val musicPlaylistCurtRoot = StandardPlaylist(
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
            standardMusicList = standardMusicList,
        )
        return flowTranData(tag, musicPlaylistCurtRoot)
    }

}