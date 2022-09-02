package com.mei.myhost

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mei.myhost.engine.FlutterInit
import com.mei.myhost.flutter.FlutterPageActivity
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Flutter 页面 以 Activity的方式显示
    // 创建一个新的 FlutterEngine 对象，渲染Flutter页面，页面启动会很慢
    fun toFlutterPage(view: View) {
        startActivity(FlutterActivity.withNewEngine().initialRoute("/").build(this))
    }

    // 使用缓存中的 FlutterEngine 对象，默认启动的Activity 为：FlutterActivity
    // 页面启动快，因为不需要重新创建 FlutterEngine 对象
    fun toFlutterPageWithCacheEngine(view: View) {
        startActivity(FlutterActivity.withCachedEngine(FlutterInit.ENGINE_ID).build(this))
    }

    // 使用自定义的 FlutterActivity，即用 FlutterPageActivity 去装载Flutter 页面
    // 页面启动快，因为不需要重新创建 FlutterEngine 对象
    fun toFlutterPageWithCustomActivity(view: View) {
        val intent = FlutterActivity.CachedEngineIntentBuilder(
            FlutterPageActivity::class.java,
            FlutterInit.ENGINE_ID
        ).build(this)
        startActivity(intent)
    }

    // Flutter 页面 以 Fragment 的方式显示
    fun toFlutterFragment(view: View) {
        startActivity(Intent(this, AddFlutterWithFragmentActivity::class.java))
    }

    // Flutter 页面 以 View的方式显示
    fun toFlutterView(view: View) {
        startActivity(Intent(this, AddFlutterWithViewActivity::class.java))
    }


}