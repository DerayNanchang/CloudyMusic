package com.lsn.comm.player.core

import com.lsn.comm.player.impl.ExoMediaPlayer
import com.lsn.comm.player.impl.IjkMediaPlayer
import com.lsn.comm.player.impl.NativeMediaPlayer


/**
 * @Author : lsn
 * @CreateTime : 2023/5/11 下午 02:58
 * @Description :
 */
class PlayerFactory {

    fun create(mode: PlayerMode): BasePlayer {


        return when (mode) {

            PlayerMode.NATIVE -> {
                NativeMediaPlayer()
            }

            PlayerMode.EXO -> {
                ExoMediaPlayer()
            }

            PlayerMode.IJK -> {
                IjkMediaPlayer()
            }

        }

    }
}