package com.pmisy.roomkb.adapter

import com.pmisy.roomkb.entity.KBInjEqDialogEntity
import com.pmisy.roomkb.entity.comm.KBEqRoomContentDetailEntity
import com.pmisy.roomkb.entity.comm.KBOrRoomContentDetailEntity
import com.pmisy.roomkb.R
import com.pmisy.roomkb.databinding.ItemDialogListBinding
import com.pmisy.roomkb.databinding.ItemKbInjEqViewBinding
import com.pmisy.roomkb.databinding.ItemKbInjOrViewBinding

/**
 * @Author : lsn
 * @CreateTime : 2023/4/4 下午 04:36
 * @Description :
 */
class CDialogAdapter :
    BaseBindAdapter<KBInjEqDialogEntity, ItemDialogListBinding>(R.layout.item_dialog_list)


class KBInjEqAdapter :
    BaseBindingCoreAdapter<KBEqRoomContentDetailEntity, ItemKbInjEqViewBinding>(R.layout.item_kb_inj_eq_view) {
    override fun onExtBindingDataHolder(data: KBEqRoomContentDetailEntity, position: Int) {
        super.onExtBindingDataHolder(data, position)
        setBGColor(binding.vView, position)
    }
}


class KBInjOrAdapter :
    BaseBindingCoreAdapter<KBOrRoomContentDetailEntity, ItemKbInjOrViewBinding>(R.layout.item_kb_inj_or_view) {

    override fun onExtBindingDataHolder(data: KBOrRoomContentDetailEntity, position: Int) {
        super.onExtBindingDataHolder(data, position)

        setBGColor(binding.vView, position)
    }
}