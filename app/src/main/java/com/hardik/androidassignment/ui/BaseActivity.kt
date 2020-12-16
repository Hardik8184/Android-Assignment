package com.hardik.androidassignment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hardik.androidassignment.MyApplication
import com.hardik.androidassignment.R
import com.hardik.androidassignment.database.DataBaseHelper
import com.hardik.androidassignment.di.component.ActivityComponent
import java.io.IOException

abstract class BaseActivity : AppCompatActivity() {

  private lateinit var activityComponent: ActivityComponent
  lateinit var dataBaseHelper: DataBaseHelper

  @SuppressLint("SourceLockedOrientationActivity")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityComponent = MyApplication.get(this).getApplicationComponent().activityBuilder()
        .activity(this)
        .build()

    try {
      dataBaseHelper = DataBaseHelper(this@BaseActivity)
    } catch (e: IOException) {
      e.printStackTrace()
    }
  }

  fun getActivityComponent(): ActivityComponent {
    return activityComponent
  }

  override fun onBackPressed() {
    super.onBackPressed()
    finish()
    overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
  }

  fun createDatabase() {
    try {
      dataBaseHelper.createDatabase()
    } catch (e: IOException) {
      e.printStackTrace()
    }
  }
}