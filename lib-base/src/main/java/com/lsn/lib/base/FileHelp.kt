package com.lsn.lib.base

import android.content.Context
import android.os.Environment
import com.lsn.lib.utils.util.PathUtils
import java.io.File


/**
 * @Author : lsn
 * @CreateTime : 2023/4/11 上午 11:01
 * @Description :
 *
 *  // 内部存储 除非 root 否则储存无法查看  data/data(user)/0/packagename/file(cache)
 *        context.filesDir
 *        context.cacheDir
 *  // 外部存储
 *      // 私有存储 29有访问权限卸载数据也会被删除
 *                  context.externalCacheDir  /storage/emulated/0/Android/data/package/files/Documents
 *                  context.getExternalFilesDir()
 *                  context.externalMediaDirs
 *      // 公共存储 共享访问应用卸载后保留数据
 *                                  /storage/emulated/0/Pictures
 */
class FileHelp private constructor() {

    companion object {
        fun get() = FileClickHelp.fileHelp
        private const val apk = "apk"
        private const val image = "image"
    }


    private object FileClickHelp {
        val fileHelp = FileHelp()

    }


    private fun createFile(file: File): File {
        if (!file.exists()) {
            file.mkdir()
        }
        return file
    }

    private fun initLiteServiceDir(fileName: String): File {

        return createFile(
            // 公共存储 应用卸载后数据可以保留
            File(
                Environment.getExternalStorageDirectory(),
                fileName
            )
        )
    }


    fun initApkDir(fileName: String): File {
        return createFile(File(initLiteServiceDir(fileName),apk))
    }


    fun initImgDir(fileName: String): File {
        return createFile(File(initLiteServiceDir(fileName), image))
    }
}