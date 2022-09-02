package com.mei.myhost

import android.app.Application
import com.mei.myhost.channel.BasicMessageChannelHelper
import com.mei.myhost.channel.CommonMethodChannel
import com.mei.myhost.engine.FlutterInit
import io.flutter.embedding.engine.FlutterEngine

/**
 * @date 2022/8/30
 * @author mxb
 * @desc
 * @desired
 */
class MyApplication : Application() {

    companion object {
        var application: Application? = null
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        FlutterInit.init(this) { engine: FlutterEngine ->
            CommonMethodChannel.initMethodCallHandler()
            BasicMessageChannelHelper.init()
        }
    }
}