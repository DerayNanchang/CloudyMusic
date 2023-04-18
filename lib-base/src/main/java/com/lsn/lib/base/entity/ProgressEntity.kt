package com.lsn.lib.base.entity

data class ProgressEntity(val msg:String?= "", val percent:Int = 0, val max:Int = 100, val outSideCancel:Boolean = false, val keyBackCancel:Boolean = false)
