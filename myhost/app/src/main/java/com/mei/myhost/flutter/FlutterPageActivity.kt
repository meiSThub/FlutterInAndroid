package com.mei.myhost.flutter

import android.util.Log
import com.mei.myhost.engine.FlutterInit
import io.flutter.embedding.android.FlutterActivity

/**
 * @date 2022/9/1
 * @author mxb
 * @desc
 * @desired
 */
class FlutterPageActivity : FlutterActivity() {
    companion object {
        private const val TAG = "FlutterPageActivity"
    }

    override fun getCachedEngineId(): String {
        Log.i(TAG, "getCachedEngineId: 调用")
        return FlutterInit.ENGINE_ID
    }
}