package com.mei.myhost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.flutter.embedding.android.FlutterFragment

class AddFlutterWithFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_flutter_with_fragment)
    }

    fun loadFragment(v: View) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(
            R.id.flContainer,
            FlutterFragment.withNewEngine().initialRoute("1000").build()
        )
        beginTransaction.commit()
    }
}