package com.lsn.module.music.entity

import java.util.concurrent.Flow


data class MusicPlaylistRoot(

    val version: Int,
    val more: Boolean,
    val playlist: List<MusicPlaylist>,
    val code: Int

)


data class MusicPlaylist(
    val adType: Int,
    val algTags: String,
    val backgroundCoverId: Int,
    val backgroundCoverUrl: String,
    val bannedTrackIds: String,
    val cloudTrackCount: Long,
    val commentCount: Int,
    val commentThreadId: String,
    val copied: Boolean,
    val coverImgId: Long,
    val coverImgId_str: String,
    val coverImgUrl: String,
    val createTime: Long,
    val creator: Creator,
    val description: String = "",
    val englishTitle: String,
    val gradeStatus: String,
    val highQuality: Boolean,
    val historySharedUsers: String,
    val id: Long,
    val mvResourceInfos: String,
    val name: String,
    val newImported: Boolean,
    val officialPlaylistType: String,
    val opRecommend: Boolean,
    val ordered: Boolean,
    val playCount: Long,
    val privacy: Int,
    val relateResType: String,
    val remixVideo: String,
    val score: String,
    val shareCount: Int,
    val sharedUsers: String,
    val specialType: Int,
    val status: Int,
    val subscribed: Boolean,
    val subscribedCount: Long,
    val subscribers: List<Subscribers>,
    val tags: List<String>,
    val titleImage: Int,
    val titleImageUrl: String,
    val trackCount: Int,
    val trackIds: List<TrackIds>,
    val trackNumberUpdateTime: Long,
    val trackUpdateTime: Long,
    val tracks: List<Tracks>?,
    val updateFrequency: String,
    val updateTime: Long,
    val userId: Long,
    val videoIds: String,
    val videos: String
)

data class Creator(
    val accountStatus: Int,
    val anchor: Boolean,
    val authStatus: Int,
    val authenticationTypes: Int,
    val authority: Int,
    val avatarDetail: String,
    val avatarImgId: Long,
    val avatarImgIdStr: String,
    val avatarImgId_str: String,
    val avatarUrl: String,
    val backgroundImgId: Long,
    val backgroundImgIdStr: String,
    val backgroundUrl: String,
    val birthday: Int,
    val city: Int,
    val defaultAvatar: Boolean,
    val description: String,
    val detailDescription: String,
    val djStatus: Int,
    val expertTags: List<String>,
    val experts: String,
    val followed: Boolean,
    val gender: Int,
    val mutual: Boolean,
    val nickname: String,
    val province: Int,
    val remarkName: String,
    val signature: String,
    val userId: Int,
    val userType: Int,
    val vipType: Int
)


data class Subscribers(
    val accountStatus: Int,
    val anchor: Boolean,
    val authStatus: Int,
    val authenticationTypes: Int,
    val authority: Int,
    val avatarDetail: Any,
    val avatarImgId: Long,
    val avatarImgIdStr: String,
    val avatarImgId_str: String,
    val avatarUrl: String,
    val backgroundImgId: Long,
    val backgroundImgIdStr: String,
    val backgroundUrl: String,
    val birthday: Int,
    val city: Int,
    val defaultAvatar: Boolean,
    val description: String,
    val detailDescription: String,
    val djStatus: Int,
    val expertTags: Any,
    val experts: Any,
    val followed: Boolean,
    val gender: Int,
    val mutual: Boolean,
    val nickname: String,
    val province: Int,
    val remarkName: Any,
    val signature: String,
    val userId: Int,
    val userType: Int,
    val vipType: Int
)


data class Tracks(
    val a: String,
    val al: Al,
    val alia: List<String>,
    val ar: List<Ar>,
    val awardTags: String,
    val cd: String,
    val cf: String,
    val copyright: Int,
    val cp: Int,
    val crbt: Any,
    val djId: Int,
    val dt: Int,
    val entertainmentTags: Any,
    val fee: Int,
    val ftype: Int,
    val h: H,
    val hr: Hr?,
    val id: Long,
    val l: L,
    val m: M,
    val mark: Long,
    val mst: Int,
    val mv: Long,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val originSongSimpleData: Any,
    val pop: Int,
    val pst: Int,
    val publishTime: Long,
    val resourceState: Boolean,
    val rt: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val s_id: Int,
    val single: Int,
    val songJumpInfo: Any,
    val sq: Sq?,
    val st: Int,
    val t: Int,
    val tagPicList: Any,
    val v: Int,
    val version: Int
)

data class Al(
    val id: Int,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<Any>
)

data class Ar(
    val alias: List<Any>,
    val id: Int,
    val name: String,
    val tns: List<Any>
)

data class H(
    val br: Int,
    val fid: Int,
    val size: Int,
    val vd: Int
)

data class L(
    val br: Int,
    val fid: Int,
    val size: Int,
    val vd: Int
)

data class M(
    val br: Int,
    val fid: Int,
    val size: Int,
    val vd: Int
)

data class Sq(
    val br: Int,
    val fid: Int,
    val size: Int,
    val vd: Int
)


data class Hr(
    val br: Long,
    val fid: Long,
    val size: Long,
    val sr: Long,
    val vd: Long
)


data class TrackIds(
    val alg: Any,
    val at: Long,
    val f: Any,
    val id: Int,
    val rcmdReason: String,
    val sc: Any,
    val sr: Any,
    val t: Int,
    val uid: Int,
    val v: Int
)