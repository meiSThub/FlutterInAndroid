package com.mei.myhost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toFlutterPage(view: View) {
        startActivity(FlutterActivity.withNewEngine().initialRoute("/?").build(this))
    }

    fun toFlutterFragment(view: View) {
        startActivity(Intent(this, AddFlutterWithFragmentActivity::class.java))
    }

    fun toFlutterView(view: View) {
        startActivity(Intent(this, AddFlutterWithViewActivity::class.java))
    }
}