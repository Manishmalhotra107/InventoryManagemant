package com.yashu.arora.inventorymanagement

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.yashu.arora.inventorymanagement.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed(Runnable {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        },1000)
    }
}