package com.lsn.module.music.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.lib.base.PermissionCategory
import com.lsn.lib.base.PermissionListener
import com.lsn.lib.base.annotation.Toolbar
import com.lsn.lib.ui.widget.rv.BindingAdapter
import com.lsn.lib.ui.widget.rv.utils.linear
import com.lsn.lib.ui.widget.rv.utils.setup
import com.lsn.module.music.R
import com.lsn.module.music.databinding.ActivityLocalMusicBinding
import com.lsn.module.music.entity.StandardMusic
import com.lsn.module.music.entity.TopPlaylist
import com.lsn.module.music.ui.viewmodel.LocalMusicViewModel
import com.uc.crashsdk.export.LogType.addType
import com.umeng.analytics.pro.t
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * @Author : lsn
 * @CreateTime : 2023/5/11 上午 10:14
 * @Description :
 */
@Toolbar("本地音乐")
@AndroidEntryPoint
class LocalMusicActivity :
    BaseCoreActivity<LocalMusicViewModel, ActivityLocalMusicBinding>(R.layout.activity_local_music) {


    private lateinit var mLocalAdapter: BindingAdapter

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        mLocalAdapter = binding.rvContent.linear().setup {
            addType<StandardMusic>(R.layout.item_playlist_detail)
//            actionDetail()
        }


    }

    override fun initData() {
        super.initData()

        requestStorageGroupPermission(object : PermissionListener{
            override fun onGranted(
                dialog: PermissionCategory,
                permissionList: MutableList<String>?
            ) {
                getLocalMusics()
            }
            override fun onDenied(doNotAskAgain: Boolean, permissions: MutableList<String>?) {
            }
        })


    }


    override fun initEvent() {
        super.initEvent()
    }


    private fun getLocalMusics() {

        lifecycleScope.launch {
            viewModel.getLocalMusics(this@LocalMusicActivity, {
                runOnUiThread {
                    mLocalAdapter.models = it
                }
            }, {


            })
        }


    }
}