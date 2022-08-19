package com.mei.myhost

import android.R.id.message
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor


class AddFlutterWithViewActivity : AppCompatActivity() {

    lateinit var flutterView: FlutterView
    lateinit var flutterContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_flutter_with_view)
        flutterView = findViewById(R.id.flutterView)
        flutterContainer = findViewById(R.id.flutterViewContainer)
    }

    fun loadFlutterView(v: View) {
        loadFlutterView()
        createFlutterView()
    }

    private fun loadFlutterView() {
        // 创建引擎
        val flutterEngine = FlutterEngine(this)
        // 路由带参数
        val route = "1001?{\"imageUrl\":\"http://pic1.win4000.com/wallpaper/7/57ba6b2a9d75e.jpg\"}"
        flutterEngine.navigationChannel.setInitialRoute(route)
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        // 渲染flutter页面
        flutterView.attachToFlutterEngine(flutterEngine)
    }

    private fun createFlutterView() {
        // 通过FlutterView引入Flutter编写的页面
        val flutterView = FlutterView(this)
        val lp = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val flContainer = findViewById<FrameLayout>(R.id.flutterViewContainer)
        flContainer.addView(flutterView, lp)

        //  创建引擎
        val flutterEngine = FlutterEngine(this)
        // 设置页面路由
        flutterEngine.navigationChannel.setInitialRoute("1001")
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        // 关键代码，将Flutter页面显示到FlutterView中
        flutterView.attachToFlutterEngine(flutterEngine)
    }
}