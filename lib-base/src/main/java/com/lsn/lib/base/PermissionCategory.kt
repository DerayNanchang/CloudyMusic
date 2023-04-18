package com.lsn.lib.base

/**
 * @Author : lsn
 * @CreateTime : 2023/3/28 上午 09:41
 * @Description :
 */
enum class PermissionCategory {

    SUCCESS, // 成功同意所有权限

    SCEPTICAL, // 部分拒绝权限

    FREFUSE, // 永远拒绝所有权限

    TREFUSE, // 暂时拒绝所有权限
}