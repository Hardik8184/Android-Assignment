package com.android.androidassignment.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.androidassignment.MyApplication
import com.android.androidassignment.R
import com.android.androidassignment.di.component.ActivityComponent

abstract class BaseActivity : AppCompatActivity() {

  private lateinit var activityComponent: ActivityComponent

  @SuppressLint("SourceLockedOrientationActivity")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityComponent = MyApplication.get(this).getApplicationComponent().activityBuilder()
        .activity(this)
        .build()
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
  }

  fun getActivityComponent(): ActivityComponent {
    return activityComponent
  }

  override fun onBackPressed() {
    super.onBackPressed()
    finish()
    overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
  }

  fun showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
  }
}