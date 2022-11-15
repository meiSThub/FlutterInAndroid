package com.mei.myhost.channel

import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mei.myhost.MyApplication
import com.mei.myhost.engine.FlutterInit
import io.flutter.plugin.common.MethodChannel

/**
 * @date 2022/8/19
 * @author mxb
 * @desc Flutter 调用 native 的方法
 * @desired
 */
object CommonMethodChannel {
    private const val TAG = "CommonMethodChannel"

    private const val FLUTTER_METHOD_COMMON_ID = "method_channel_common"
    private val methodChannel by lazy {
        MethodChannel(FlutterInit.getEngine()!!.dartExecutor, FLUTTER_METHOD_COMMON_ID)
    }

    fun initMethodCallHandler() {
        methodChannel.setMethodCallHandler { call, result ->
            Log.i(TAG, "方法分发：method=${call.method},arguments=${call.arguments}")
            when (call.method) {
                "getBatteryLevel" -> result.success(getBatteryLevel())
                else -> Log.i(TAG, "initMethodCallHandler: 未实现方法：${call.method}")
            }
        }
    }

    /**
     * 获取电量信息
     * @return
     */
    private fun getBatteryLevel(): Int {
        val context = MyApplication.application!!
        val batteryLevel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val batteryManager =
                context.getSystemService(AppCompatActivity.BATTERY_SERVICE) as BatteryManager
            batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else {
            val intent = ContextWrapper(context).registerReceiver(
                null,
                IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            )
            intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 /
                intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        }
        return batteryLevel
    }
}
