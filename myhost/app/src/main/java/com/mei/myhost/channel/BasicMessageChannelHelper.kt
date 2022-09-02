package com.mei.myhost.channel

import android.util.Log
import com.mei.myhost.engine.FlutterInit
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.StringCodec

/**
 * @date 2022/8/20
 * @author mxb
 * @desc BasicMessageChannel 消息通道，发送和回复消息
 * @desired
 */
object BasicMessageChannelHelper : BasicMessageChannel.MessageHandler<String>,
    BasicMessageChannel.Reply<String> {

    private const val TAG = "BasicMessageChannelHelp"
    private const val CHANNEL_NAME = "basic_message_channel_common"

    private val messageChannel: BasicMessageChannel<String> by lazy {
        BasicMessageChannel(
            FlutterInit.getEngine()!!.dartExecutor,
            CHANNEL_NAME,
            StringCodec.INSTANCE
        )
    }

    fun init() {
        messageChannel.setMessageHandler(this)
    }

    override fun onMessage(message: String?, reply: BasicMessageChannel.Reply<String>) {
        Log.i(TAG, "onMessage: receive message from flutter:$message")
        reply.reply("Native received message")
        messageChannel.send("Hello,I am Android")
    }

    override fun reply(reply: String?) {

    }
}