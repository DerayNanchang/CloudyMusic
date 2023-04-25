package com.pmisy.roomkb


/**
 * @Author : lsn
 * @CreateTime : 2023/4/6 上午 11:33
 * @Description :
 */
interface EntranceConstants {

    interface EQ {

        companion object {

            var MACHINE_STATUS_KEY = arrayListOf(
                "All",
                "RUN",
                "HRUN",
                "STOP", //
                "WAIT",
                "FAUL",
                "MAIN",
                "OFFL",
                "DELE",
            )
            var MACHINE_COLOR_STATUS = arrayListOf(
                "#333333",
                "#2FC25B",      // 全自动
                "#41D9C7",      // 半自动
                "#808080",      // 停机
                "#8543E0",      // 待机
                "#FB411B",      // 故障
                "#01C4E2",      // 保养
                "#FF6A00",      // 离线
                "#111111",      // 删除
            )

            var MACHINE_DESC_STATUS = arrayListOf(
                "全部",
                "全自动",
                "半自动",
                "停机", //
                "手动",
                "故障",
                "保养",
                "离线",
                "注销",
            )
        }

    }

    interface EVENT {

        companion object {

            const val KB_EQ_ROOM_NEW_DATA = "KB_EQ_ROOM_NEW_DATA"

        }
    }

}