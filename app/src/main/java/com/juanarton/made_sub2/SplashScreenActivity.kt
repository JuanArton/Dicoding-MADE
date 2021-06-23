package com.juanarton.made_sub2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.juanarton.made_sub2.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        private const val TIME_FOR_SPLASH = 2000L
    }

    private var bindingtmp: ActivitySplashScreenBinding? = null
    private val binding get() = bindingtmp!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingtmp = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        supportActionBar?.hide()
        val splashHandler = Handler(mainLooper)

        splashHandler.postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, TIME_FOR_SPLASH)
    }

    override fun onDestroy() {
        bindingtmp = null
        super.onDestroy()
    }
}