package com.lsn.lib.base.annotation

import android.graphics.Color
import com.lsn.lib.base.StatusColorCategory
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @Author : lsn
 * @CreateTime : 2023/3/20 下午 02:55
 * @Description :
 */
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(RetentionPolicy.RUNTIME)
annotation class Toolbar(
    val title: String = "",
    val showBack: Boolean = true,
    val isAdd: Boolean = true,
    val color: StatusColorCategory = StatusColorCategory.WHITE_COLOR
)
