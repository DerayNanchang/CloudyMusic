package com.lsn.cloudymusic

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.lsn.cloudy.R
import com.lsn.cloudy.databinding.ActivityMainBinding
import com.lsn.comm.core.ui.activity.BaseCoreActivity
import com.lsn.comm.core.ui.fragment.BaseCoreFragment
import com.lsn.lib.base.annotation.Toolbar
import com.lsn.module.provider.main.provide.*
import com.pmisy.roomkb.Constants

@Route(path = Constants.RouterPath.MAIN.INDEX)
@Toolbar(title = "Welcome", showBack = false)
class MainActivity : BaseCoreActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {


    @JvmField
    @Autowired(name = Constants.RouterPath.MUSIC.PROVIDE)
    var musicProvider: MusicProvider? = null

    @JvmField
    @Autowired(name = Constants.RouterPath.VIDEO.PROVIDE)
    var videoProvider: VideoProvider? = null

    @JvmField
    @Autowired(name = Constants.RouterPath.GRAPHIC.PROVIDE)
    var graphicProvider: GraphicProvider? = null

    @JvmField
    @Autowired(name = Constants.RouterPath.MINE.PROVIDE)
    var mineProvider: MineProvider? = null


    private var mCurrentFragment: Fragment? = null
    private var mMusicHomeFragment: Fragment? = null
    private var mVideoHomeFragment: Fragment? = null
    private var mGraphicHomeFragment: Fragment? = null
    private var mSettingsHomeFragment: Fragment? = null


    private lateinit var mTvMainMusicHomeMsg: TextView
    private lateinit var mTvMainVideoHomeMsg: TextView
    private lateinit var mTvMainGraphicHomeMsg: TextView
    private lateinit var mTvMainMineMsg: TextView


    private val badgeIds = intArrayOf(
        R.layout.item_badge_music,
        R.layout.item_badge_video,
        R.layout.item_badge_graphic,
        R.layout.item_badge_mine
    )
    private val msgIds = intArrayOf(
        R.id.tv_main_music_msg,
        R.id.tv_main_video_msg,
        R.id.tv_main_graphic_msg,
        R.id.tv_main_mine_msg
    )


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        binding.navView.itemIconTintList = null
        musicProvider?.getMusicHomeFragment()
        switchToMusic()
        checkIfShowSavedFragment(savedInstanceState)
        addTabBadge()
    }


    private fun switchToMusic() {
        if (mMusicHomeFragment == null) {
            mMusicHomeFragment = musicProvider?.getMusicHomeFragment()
        }
        replace(mMusicHomeFragment, "music")
//        PStatusBarUtil.setResStatusBar(this, R.color.F6F5F3, false, true)
    }


    /**
     * 用于展示是否已经存在的Fragment
     *
     * @param savedInstanceState
     */
    private fun checkIfShowSavedFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val tag = savedInstanceState.getString("tag")
            if (!TextUtils.isEmpty(tag)) {
                val fragment = supportFragmentManager.findFragmentByTag(tag)
                if (fragment is BaseCoreFragment<*, *>) {
                    replace(fragment, tag)
                }
            }
        }
    }


    /**
     * 添加BottomNavigationView中每个item右上角的红点
     */
    private fun addTabBadge() {
        val menuView = binding.navView.getChildAt(0) as BottomNavigationMenuView
        val childCount = menuView.childCount
//        Log.e(TAG, "bottom child count = $childCount")
        var itemTab: BottomNavigationItemView
        for (i in 0 until childCount) {
            itemTab = menuView.getChildAt(i) as BottomNavigationItemView
            val badge = LayoutInflater.from(this).inflate(badgeIds[i], menuView, false)
            when (i) {
                0 -> mTvMainMusicHomeMsg = badge.findViewById(msgIds[0])
                1 -> mTvMainVideoHomeMsg = badge.findViewById(msgIds[1])
                2 -> mTvMainGraphicHomeMsg = badge.findViewById(msgIds[2])
                3 -> mTvMainMineMsg = badge.findViewById(msgIds[3])
            }
            itemTab.addView(badge)
        }
    }


    private fun replace(fragment: Fragment?, tag: String?) {
        fragment?.apply {
            if (mCurrentFragment != this) {
                val t = supportFragmentManager.beginTransaction()
                mCurrentFragment?.let {
                    t.hide(it)
                }
                mCurrentFragment = this
                if (!this.isAdded) {
                    if (tag != null) {
                        t.add(R.id.fl_main_fragment, this, tag).show(this).commit()
                    }
                } else {
                    t.show(this).commit()
                }
            }
        }
    }

}