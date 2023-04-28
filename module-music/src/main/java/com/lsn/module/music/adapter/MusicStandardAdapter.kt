package com.lsn.module.music.adapter

import com.lsn.lib.base.ui.adapter.BaseBindAdapter
import com.lsn.module.music.R
import com.lsn.module.music.databinding.ItemHomeCommBinding
import com.lsn.module.music.databinding.ItemPersonalizedBinding
import com.lsn.module.music.entity.HomeSimpleItemData
import com.lsn.module.music.entity.MusicPersonalized

/**
 * @Author : lsn
 * @CreateTime : 2023/4/28 上午 09:54
 * @Description :
 */
class MusicHomeSimpleItemAdapter : BaseBindAdapter<HomeSimpleItemData, ItemHomeCommBinding>(R.layout.item_home_comm)

class MusicPersonalizedAdapter : BaseBindAdapter<MusicPersonalized, ItemPersonalizedBinding>(R.layout.item_personalized)