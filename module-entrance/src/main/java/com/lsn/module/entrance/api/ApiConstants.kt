package com.lsn.module.entrance.api


/**
 * @Author : lsn
 * @CreateTime : 2023/4/3 下午 03:52
 * @Description :
 */
interface ApiConstants {

    interface Comm {
        companion object {
            // 登录
            const val LOGIN = "user/login.do"

            // 查询Apk信息
            const val SEARCH_APK_INFO = "mobile/pub/searchAPKInfo.do"

            // 查询车间
            const val SEARCH_ROOM = "mobile/remotecall/searchRoom.do"

            //注塑车间 机器数据
            const val SEARCH_PROD_EQUIPMENT_DETAIL_LIST =
                "mobile/om/searchProdEquipmentDetailList.do"

            // 注塑车间 订单数据
            const val SEARCH_PROD_ORDER_DETAIL_LIST = "mobile/om/searchProdOrderDetailList.do"


        }
    }


}