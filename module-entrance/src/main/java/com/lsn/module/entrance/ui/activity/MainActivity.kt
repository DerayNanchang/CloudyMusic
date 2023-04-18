package com.pmisy.roomkb.ui.activity

import android.content.Intent
import com.lsn.comm.core.exts.comm.startActivity
import com.lsn.comm.mqtt.MQConstant
import com.lsn.comm.mqtt.callback.MQCallback
import com.lsn.comm.mqtt.core.MQClient
import com.lsn.comm.mqtt.util.MQUtils.macSignature
import com.lsn.lib.base.PARA
import com.pmisy.roomkb.R
import com.pmisy.roomkb.app.PMIJEKanbanApplication
import com.pmisy.roomkb.databinding.ActivityMainBinding
import com.pmisy.roomkb.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttMessage

@AndroidEntryPoint
class MainActivity :
    BaseKanbanActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main),
    MQCallback {

    private var mqClient: MQClient? = null

    override fun initView() {
        super.initView()
//        initMQTT()
    }


    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        super.initEvent()

        binding.llEqui.setOnClickListener {
            // 设备
            startActivity<RoomActivity>(PARA.INT_ID to PARA.VALUE_INT_ID)
        }


        binding.llOrder.setOnClickListener {
            // 订单
            startActivity<RoomActivity>(PARA.INT_ID to PARA.VALUE_INT_ID_2)
        }


        binding.llAssemble.setOnClickListener {
            // 装配
            startActivity<RoomActivity>(PARA.INT_ID to PARA.VALUE_INT_ID_3)
        }

    }


    private fun initMQTT() {
        CoroutineScope(Dispatchers.IO)
            .launch {
                val mIdentity = PMIJEKanbanApplication.application.getIdentity()
                val mTopList =
                    arrayListOf(MQConstant.TOPIC_1, MQConstant.TOPIC_2, MQConstant.TOPIC_3)
                val nQosList = arrayListOf(1, 1, 1)
                val mUserName = "Signature|" + MQConstant.ACCESS_KEY + "|" + MQConstant.INSTANCE_ID
                val mPassword = macSignature(MQConstant.CLIENT_ID, MQConstant.SECRET_KEY)
                mqClient = MQClient.build {
                    ctx = applicationContext
                    this.identity = mIdentity
                    topicList = mTopList
                    qosList = nQosList
                    username = mUserName
                    password = mPassword
                }.connect().callback(this@MainActivity).own()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        mqClient?.close()
        mqClient = null
    }

    override fun connectionLost(cause: Throwable) {

    }

    override fun messageArrived(topic: String, message: MqttMessage) {

    }

    override fun deliveryComplete(token: IMqttDeliveryToken) {

    }

}
