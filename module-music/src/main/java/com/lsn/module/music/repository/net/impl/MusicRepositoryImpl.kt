package com.lsn.module.music.repository.net.impl

import com.lsn.comm.core.net.ResponseEntity
import com.lsn.comm.core.net.flowTranData
import com.lsn.lib.ui.widget.banner.widget.banner.BannerItem
import com.lsn.module.music.entity.HomeSimpleItemData
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

}