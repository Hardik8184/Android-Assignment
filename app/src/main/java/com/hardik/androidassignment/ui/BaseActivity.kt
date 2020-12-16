package com.hardik.androidassignment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hardik.androidassignment.MyApplication
import com.hardik.androidassignment.R
import com.hardik.androidassignment.di.component.ActivityComponent

abstract class BaseActivity : AppCompatActivity() {

  private lateinit var activityComponent: ActivityComponent

  @SuppressLint("SourceLockedOrientationActivity")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityComponent = MyApplication.get(this).getApplicationComponent().activityBuilder()
        .activity(this)
        .build()
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