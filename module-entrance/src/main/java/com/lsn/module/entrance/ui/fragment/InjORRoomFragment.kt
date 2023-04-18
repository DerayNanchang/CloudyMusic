package com.pmisy.roomkb.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import com.lsn.comm.core.ui.fragment.BaseCoreFragment
import com.lsn.lib.base.bus.LiveBus
import com.pmisy.roomkb.R
import com.pmisy.roomkb.databinding.FragmentInjOrRoomBinding
import com.lsn.lib.utils.util.CollectionUtils
import com.lsn.lib.utils.util.MToast
import com.pmisy.roomkb.Constants
import com.pmisy.roomkb.adapter.RecyclerViewBannerAdapter
import com.pmisy.roomkb.api.ApiConstants
import com.pmisy.roomkb.entity.comm.KBORRoomEntity
import com.pmisy.roomkb.ui.activity.RoomActivity
import com.pmisy.roomkb.ui.viewmodel.InjORRoomViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * @Author : lsn
 * @CreateTime : 2023/4/6 下午 06:41
 * @Description :
 */
@AndroidEntryPoint
class InjORRoomFragment : BaseCoreFragment<InjORRoomViewModel,FragmentInjOrRoomBinding>(R.layout.fragment_inj_or_room) {

    private var plantNo = "1000"

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun initData() {
        super.initData()

        if (TextUtils.isEmpty(getSelectRoomId()) || TextUtils.isEmpty(getSelectDate())) {
            MToast.show("请选择上面的参数")
            return
        }

        viewModel.getKBEQBodyData(
            plantNo,
            getSelectRoomId()!!,
            getSelectDate()!!
        )

    }

    override fun initEvent() {
        super.initEvent()

        LiveBus.get().with(Constants.EVENT.KB_EQ_ROOM_NEW_DATA).observe(this){

            if (TextUtils.isEmpty(getSelectRoomId()) || TextUtils.isEmpty(getSelectDate())) {
                MToast.show("请选择上面的参数")
                return@observe
            }

            viewModel.getKBEQBodyData(
                plantNo,
                getSelectRoomId()!!,
                getSelectDate()!!
            )
        }
    }


    override fun onResponseReceiver() {
        super.onResponseReceiver()

        viewModel.success.observe(this) {
            when (it.api) {

                ApiConstants.Comm.SEARCH_PROD_ORDER_DETAIL_LIST -> {
                    val kbORRoomEntity = it.data as KBORRoomEntity
                    val equipmentDetails = kbORRoomEntity.list
                    equipmentDetails?.let {
                        val page = ArrayList<String>()
                        val splitList = splitList(equipmentDetails!!, getKanbanOrMaxCountOr())
                        for (i in 0 until splitList!!.size) {
                            page.add("")
                        }
                        binding.blVertical.setAdapter(
                            com.pmisy.roomkb.adapter.RecyclerViewBannerAdapter(
                                page,
                                context,
                                null,
                                splitList
                            )
                        )

                        binding.blVertical.setAutoPlaying(true)
                    }
                }
            }

        }
    }


    private fun getKanbanOrMaxCountOr(): Int {
        if (getKBRoomActivity() == null) {
            return 10
        }
        return getKBRoomActivity()!!.kanbanMaxCountOr
    }


    private fun getSelectRoomId(): String? {
        if (getKBRoomActivity() == null) {
            return null
        }
        return getKBRoomActivity()!!.selectRoomId
    }


    private fun getSelectDate(): String? {
        if (getKBRoomActivity() == null) {
            return null
        }
        return getKBRoomActivity()!!.dateStr
    }

    private fun getKBRoomActivity(): RoomActivity? {
        if (activity == null) {
            return null
        }
        if (requireActivity() is RoomActivity) {
            return requireActivity() as RoomActivity
        } else {
            return null
        }
    }


    open fun <T> splitList(list: List<T>, num: Int): ArrayList<ArrayList<T>> {
        val listSize = list.size
        val objects1: ArrayList<ArrayList<T>> = ArrayList()
        var n = 0
        for (i in 0 until listSize / num) {
            val objects: java.util.ArrayList<T> = java.util.ArrayList()
            while (n < num * (i + 1)) {
                objects.add(list[n])
                n++
            }
            objects1.add(objects)
        }
        //处理余数问题
        val a = if (listSize % num != 0) listSize % num else 0
        for (i in 0 until a) {
            val objects: java.util.ArrayList<T> = java.util.ArrayList()
            while (n < num * (listSize / num) + a) {
                objects.add(list[n])
                n++
            }
            if (!CollectionUtils.isEmpty(objects)) {
                objects1.add(objects)
            }
        }
        return objects1
    }
}