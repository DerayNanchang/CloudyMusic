package com.lsn.module.music.entity


/**
 * @Author : lsn
 * @CreateTime : 2023/5/2 上午 09:37
 * @Description :
 */
data class MusicPlaylistDetailRoot(
    val code : Int,
    val relatedVideos : String,
    val playlist : MusicPlaylist,
    val urls : String,
    val privileges : List<Privileges>,
    val sharedPrivilege : String,
    val resEntrance : String,
    val fromUsers : String,
    val fromUserCount : Int,
    val songFromUsers : String,
)



data class Privileges(
    val chargeInfoList: List<ChargeInfo>,
    val cp: Int,
    val cs: Boolean,
    val dl: Int,
    val dlLevel: String,
    val downloadMaxBrLevel: String,
    val downloadMaxbr: Int,
    val fee: Int,
    val fl: Int,
    val flLevel: String,
    val flag: Int,
    val freeTrialPrivilege: FreeTrialPrivilege,
    val id: Int,
    val maxBrLevel: String,
    val maxbr: Int,
    val paidBigBang: Boolean,
    val payed: Int,
    val pc: Any,
    val pl: Int,
    val plLevel: String,
    val playMaxBrLevel: String,
    val playMaxbr: Int,
    val preSell: Boolean,
    val realPayed: Int,
    val rscl: Any,
    val sp: Int,
    val st: Int,
    val subp: Int,
    val toast: Boolean
)

data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Int,
    val chargeUrl: Any,
    val rate: Int
)

data class FreeTrialPrivilege(
    val listenType: Any,
    val resConsumable: Boolean,
    val userConsumable: Boolean
)

