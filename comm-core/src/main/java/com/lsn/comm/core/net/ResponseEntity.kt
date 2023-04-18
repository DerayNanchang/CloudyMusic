package com.lsn.comm.core.net


/**
 * @Author : lsn
 * @CreateTime : 2023/3/29 下午 03:16
 * @Description :
 */
data class ResponseEntity(
    var api: String,
    var data: Any,
    var page: Int = 0,
    var size: Int = 0,
    var countSize: Int = 0
)