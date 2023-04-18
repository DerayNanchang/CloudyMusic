package com.pmisy.roomkb.ui.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lsn.lib.base.PARA
import com.lsn.lib.base.bus.LiveBus
import com.pmisy.roomkb.databinding.ActivityRoomBinding
import com.lsn.lib.utils.util.DateUtil
import com.lsn.lib.utils.util.MToast
import com.pmisy.roomkb.Constants
import com.pmisy.roomkb.adapter.BaseBindAdapter
import com.pmisy.roomkb.adapter.CDialogAdapter
import com.pmisy.roomkb.api.ApiConstants
import com.pmisy.roomkb.entity.KBInjEqDialogEntity
import com.pmisy.roomkb.entity.comm.RoomEntity
import com.pmisy.roomkb.manager.SPManager
import com.pmisy.roomkb.ui.fragment.AssRoomFragment
import com.pmisy.roomkb.ui.fragment.InjEQRoomFragment
import com.pmisy.roomkb.ui.fragment.InjORRoomFragment
import com.pmisy.roomkb.ui.viewmodel.InjRoomViewModel
import com.pmisy.roomkb.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


/**
 * @Author : lsn
 * @CreateTime : 2023/4/6 下午 06:05
 * @Description :
 */
@AndroidEntryPoint
class RoomActivity :
    BaseKanbanActivity<InjRoomViewModel, ActivityRoomBinding>(R.layout.activity_room) {

    open var dateStr: String? = null
    private var mDatePickerDialog: DatePickerDialog? = null
    private var pushDialog: AlertDialog? = null
    private var roomDialog: AlertDialog? = null

    private var workshopDataList: ArrayList<KBInjEqDialogEntity> = ArrayList()
    private var tvSubmit: TextView? = null
    private var tvCancel: TextView? = null
    var kanbanMaxCount = 10
    var kanbanMaxCountOr = 5
    private var etMaxPageSize: AppCompatEditText? = null
    private var cDialogAdapter: CDialogAdapter? = null
    open var selectRoomId = "A01"  //选择的车间
    private var plantNo = "1000"
    private var routeType = "A"
    private val fm = supportFragmentManager
    private var tag: Int = 0

    override fun initView() {
        super.initView()

        tag = intent.getIntExtra(PARA.INT_ID, 0)
        kanbanMaxCount = SPManager.instance.getKanbanMaxCount()
        kanbanMaxCountOr = SPManager.instance.getKanbanMaxCountOR()
        binding.tvDate.requestFocus()
        initDialog()
        if (tag == PARA.VALUE_INT_ID) {
            setupFragment(InjEQRoomFragment())
        } else if (tag == PARA.VALUE_INT_ID_2) {
            setupFragment(InjORRoomFragment())
        }else if (tag == PARA.VALUE_INT_ID_3){
            setupFragment(AssRoomFragment())
        }
    }

    @SuppressLint("ResourceType")
    override fun initData() {
        super.initData()

        dateStr = DateUtil.convertSecondsToFormat(System.currentTimeMillis(), DateUtil.YYYY02MM02DD)

        // 设置日期
        var dialog = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            val instance = Calendar.getInstance()
            instance.set(year, month, day)
            dateStr = DateUtil.convertSecondsToFormat(instance.timeInMillis, DateUtil.YYYY02MM02DD)
            binding.tvDate.text = "日期 : " + dateStr
            LiveBus.get().with(Constants.EVENT.KB_EQ_ROOM_NEW_DATA).postValue(0)
        }

        mDatePickerDialog = DatePickerDialog(
            this,
            4,
            dialog,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )

        if (!TextUtils.isEmpty(dateStr)) {
            binding.tvDate.text = "日期 : $dateStr"
        }


        if (!TextUtils.isEmpty(dateStr)) {
            binding.tvDate.text = "日期 : $dateStr"
            viewModel.getRoomData(
                plantNo,
                routeType
            )
        }


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        event?.let {

            if (it.keyCode == KeyEvent.KEYCODE_DPAD_LEFT){


            }
        }

        return super.onKeyDown(keyCode, event)
    }

 /*   override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return super.onKeyDown(keyCode, event)



    }*/


    override fun initEvent() {
        super.initEvent()


        binding.tvDate.setOnClickListener {
            mDatePickerDialog?.let {
                if (!it.isShowing) {
                    it.show()
                }
            }
        }


        binding.ivSettings.setOnClickListener {
            pushDialog?.let {
                if (!it.isShowing) {
                    it.show()
                }
            }
        }

        tvCancel?.setOnClickListener {
            pushDialog?.dismiss()
        }


        tvSubmit?.setOnClickListener {
            val text = etMaxPageSize?.text
            text?.let {
                if (tag == 0) {
                    SPManager.instance.putKanbanMaxCount(it.toString().toInt())
                } else if (tag == 1) {
                    SPManager.instance.putKanbanMaxCountOr(it.toString().toInt())
                }
            }
            pushDialog?.dismiss()
        }


        cDialogAdapter?.setOnItemClickListener(object : BaseBindAdapter.OnItemClickListener {
            override fun onItemClick(pposition: Int, pdata: Any) {
                if (TextUtils.isEmpty(dateStr)) {
                    MToast.show("请选择时间")
                    roomDialog?.dismiss()
                    return
                }

                val dialogEntity = pdata as KBInjEqDialogEntity
                this@RoomActivity.selectRoomId = dialogEntity.mId

                LiveBus.get().with(Constants.EVENT.KB_EQ_ROOM_NEW_DATA).postValue(0)
                binding.tvRoom.text = dialogEntity.text
                roomDialog?.dismiss()
            }
        })


        binding.tvRoom.setOnClickListener {

            if (workshopDataList.size == 0) {
                MToast.show("车间数据未找到")
                return@setOnClickListener
            }

            workshopDataList.let {
                roomDialog?.show()
            }
        }
    }


    override fun onResponseReceiver() {
        super.onResponseReceiver()

        viewModel.success.observe(this) {
            when (it.api) {
                ApiConstants.Comm.SEARCH_ROOM -> {
                    val roomEntities = it.data as ArrayList<RoomEntity>
                    for (i in roomEntities.indices) {
                        if (i == 0) {
                            binding.tvRoom.text = roomEntities[i].description
                            selectRoomId = roomEntities[i].roomNo
                        }
                        val dialogEntity = KBInjEqDialogEntity()
                        dialogEntity.mId = roomEntities[i].roomNo
                        dialogEntity.icon = R.mipmap.ic_menu03
                        dialogEntity.text = roomEntities[i].description
                        dialogEntity.ext1 = roomEntities[i].plantNo
                        dialogEntity.ext2 = roomEntities[i].roomNo
                        workshopDataList.add(dialogEntity)
                    }
                    cDialogAdapter?.setData(workshopDataList)
                }
            }
        }
    }


    //------local method-----
    private fun setupFragment(targetFragment: Fragment) {
        val transaction: FragmentTransaction = fm.beginTransaction()
        transaction.add(R.id.flContent, targetFragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }


    private fun initDialog() {

        val settingsDialogView =
            LayoutInflater.from(this)
                .inflate(R.layout.dialog_setting_max_item, null, false)
        etMaxPageSize = settingsDialogView.findViewById(R.id.etMaxPageSize)
        tvSubmit = settingsDialogView.findViewById(R.id.tvSubmit)
        tvCancel = settingsDialogView.findViewById(R.id.tvCancel)
        if (tag == 0) {
            etMaxPageSize?.setText(kanbanMaxCount.toString())
        } else if (tag == 1) {
            etMaxPageSize?.setText(kanbanMaxCountOr.toString())
        }
        pushDialog = AlertDialog.Builder(this)
            .setView(settingsDialogView)
            .create()


        val roomDialogBuild =
            LayoutInflater.from(this).inflate(R.layout.dialog_list, null, false)
        var rvContent =
            roomDialogBuild.findViewById<RecyclerView>(com.pmisy.roomkb.R.id.rvContent)

        cDialogAdapter = CDialogAdapter()
        rvContent.apply {
            adapter = cDialogAdapter
            layoutManager = LinearLayoutManager(context)
        }
        roomDialog = AlertDialog.Builder(this)
            .setView(roomDialogBuild)
            .create()

    }


}