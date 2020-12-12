package com.android.androidassignment.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.android.androidassignment.di.scope.ActivityContext
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule {

  @Provides
  @ActivityContext
  fun context(activity: BaseActivity): Context {
    return activity
  }

  @Provides
  fun activity(activity: BaseActivity): AppCompatActivity {
    return activity
  }

  @get:Provides
  val compositeDisposable: CompositeDisposable
    get() = CompositeDisposable()

}