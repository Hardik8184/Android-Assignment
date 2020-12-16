package com.hardik.androidassignment.ui.main.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.hardik.androidassignment.R
import com.hardik.androidassignment.databinding.ActivitySplashScreenBinding
import com.hardik.androidassignment.ui.BaseActivity
import com.hardik.androidassignment.ui.main.main_activity.MainActivity

class SplashScreenActivity : BaseActivity() {

  lateinit var binding: ActivitySplashScreenBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    getActivityComponent().inject(this)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
    goToScreen()
  }

  private fun goToScreen() {

    Handler(Looper.getMainLooper()).postDelayed({

      startActivity(Intent(this, MainActivity::class.java))
      overridePendingTransition(R.anim.enter, R.anim.exit)
      finish()

    }, 4000)
  }
}