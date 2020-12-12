//package com.android.androidassignment
package com.android.androidassignment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDexApplication
import com.android.androidassignment.di.component.ApplicationComponent

class MyApplication : MultiDexApplication() {

  private lateinit var applicationComponent: ApplicationComponent

  companion object {
    fun get(activity: AppCompatActivity): MyApplication {
      return activity.application as MyApplication
    }

    fun get(activity: Context): MyApplication {
      return activity as MyApplication
    }
  }

  override fun onCreate() {
    super.onCreate()
    applicationComponent = DaggerApplicationComponent.builder()
        .application(this)
        .build()
    applicationComponent.inject(this)
  }

  fun getApplicationComponent(): ApplicationComponent {
    return applicationComponent
  }
}