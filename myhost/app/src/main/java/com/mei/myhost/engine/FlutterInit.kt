package com.mei.myhost.engine

import android.app.Application
import com.mei.myhost.engine.plugin.MyFlutterPlugin
import com.mei.myhost.engine.utils.FlutterUtils
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor.DartEntrypoint
import io.flutter.view.FlutterMain


/**
 * FlutterEngine 创建成功的回调
 */
typealias onStart = (engine: FlutterEngine) -> Unit

/**
 * @date 2022/8/30
 * @author mxb
 * @desc
 * @desired
 */
object FlutterInit {

    const val ENGINE_ID = "cached_engine_id"

    // FlutterPlugin 插件
    private var flutterPlugin: MyFlutterPlugin? = null

    /**
     * flutter 初始化
     * @param application
     * @param callback  FlutterEngine 创建成功的回调
     */
    fun init(application: Application, callback: onStart) {
        // 获取缓存的 FlutterEngine 对象
        var engine = getEngine()

        if (engine == null) {
            engine = FlutterEngine(application, null)
            FlutterEngineCache.getInstance().put(ENGINE_ID,engine)
        }

        if (!engine.dartExecutor.isExecutingDart) {
            // Pre-warm the cached FlutterEngine.
            engine.navigationChannel.setInitialRoute("/")
            engine.dartExecutor.executeDartEntrypoint(
                DartEntrypoint(
                    FlutterMain.findAppBundlePath(), "main"
                )
            )
        }
        // FlutterEngine 创建成功
        callback.invoke(engine)

        // 关联 FlutterPlugin
        engine.plugins.add(MyFlutterPlugin())
        // getPlugin()
    }

    fun getEngine(): FlutterEngine? = FlutterEngineCache.getInstance().get(ENGINE_ID)

    fun getPlugin(): MyFlutterPlugin? {
        if (flutterPlugin == null) {
            val engine = getEngine()
                ?: throw RuntimeException("FlutterBoost might *not* have been initialized yet!!!")

            flutterPlugin = FlutterUtils.getPlugin(engine)
        }
        return flutterPlugin
    }
}

