package com.pmisy.roomkb.entity.comm

/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 上午 10:43
 * @Description :
 */


data class VersionEntity(
    val buildUpdateDescription: String = "",
    val buildVersion: Int = 0,
    val needForceUpdate: Int = 0,   // 0不强制更新 1 强制更新
    val buildVersionNo: String = "",
    val downloadURL: String = "",
)

data class KBORRoomEntity(
    var list: List<KBOrRoomContentDetailEntity>? = null,
    var map: KBOrRoomBottomDetailEntity? = null
)


data class KBOrRoomContentDetailEntity(
    val badSize: Int = 0,
    val colour: String = "",
    val deliveryDate: String = "",
    val equipmentNo: String = "",
    val finishRate: Double = 0.0,
    val goodRate: Double = 0.0,
    val goodSize: Int = 0,
    val orderNo: String = "",
    val orderSize: Int = 0,
    val prodName: String = "",
    val prodSize: Double = 0.0,
    val remainder: Double = 0.0,
    val status: String = "",
    val supplies: String = "",
    val turnSize: Int = 0
)

data class KBOrRoomBottomDetailEntity(
    val availableCount: Int = 0,
    val delayedCount: Int = 0,
    val equipmentCount: Int = 0,
    val normalCount: Int = 0,
    val onTimeRate: Double = 0.0,
    val orderCount: Int = 0,
    val usageRate: Double = 0.0,
    val usedCount: Int = 0
)


data class KBEqRoomEntity(
    var list: List<KBEqRoomContentDetailEntity>? = null,
    var map: KBEqRoomBottomDetailEntity? = null
)


data class KBEqRoomContentDetailEntity(
    var equipmentNo: String = "",
    var runTime: Double = 0.0,
    var stopTime: Double = 0.0,
    var rate1: Double = 0.0,
    var rate2: Double = 0.0,
    var actualCycle: Double = 0.0,
    var standardCycle: Double = 0.0,
    val alarmsCount: Double = 0.0,
    var oEE: Double = 0.0,
    var statusDes: String = "",
    var statusNo: String = "",
    var type: Int = 0,
) {
    fun getMOEE(): Double {
        return oEE
    }
}


data class KBEqRoomBottomDetailEntity(
    var runCount: Int = 0,
    var hRunCount: Int = 0,
    var waitCount: Int = 0,
    var stopCount: Int = 0,
    var faultCount: Int = 0,
    var mainCount: Int = 0,
    var bootRate: Double = 0.0,
    var workRate: Double = 0.0,
    var warnCount: Int = 0,
    var slowCount: Int = 0,
)


data class RoomEntity(
    var roomNo: String = "",
    var routeType: String = "",
    var description: String = "",
    var plantNo: String = "",
    var areaNo: String = "",
    var locationNo: String = "",
    var shortCode: String = "",
    var total: Int = 0,
    //开机率
    var openRate: Double = 0.0,
    //稼动率
    var workRate: Double = 0.0,
    //警告
    var warn: Int = 0,
    //状态
    var statusNo: String = "",
    //生产
    var run: Double = 0.0,
    //待机
    var wait: Double = 0.0,
    //停机
    var stop: Double = 0.0,
    //保养
    var main: Double = 0.0,
    //故障
    var faul: Double = 0.0,
    //离线
    var offl: Double = 0.0,
    var isClick: Boolean = false
)
