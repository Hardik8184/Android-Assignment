package com.android.androidassignment.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.android.androidassignment.R
import com.android.androidassignment.databinding.ActivitySplashBinding
import com.android.androidassignment.ui.BaseActivity

class SplashScreenActivity : BaseActivity() {

  lateinit var binding: ActivitySplashBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    getActivityComponent().inject(this)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    goToScreen()
  }

  private fun goToScreen() {

    Handler(Looper.getMainLooper()).postDelayed({

//      startActivity(Intent(this, LoginActivity::class.java))
//      overridePendingTransition(R.anim.enter, R.anim.exit)
//      finish()

    }, 2000)
  }
}