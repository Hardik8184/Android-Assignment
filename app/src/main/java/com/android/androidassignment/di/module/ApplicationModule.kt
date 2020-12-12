package com.android.androidassignment.di.module

import android.app.Application
import android.content.Context
import com.android.androidassignment.MyApplication
import com.android.androidassignment.di.scope.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule() {

  @Provides
  @ApplicationContext
  fun provideContext(myApplication: MyApplication): Context {
    return myApplication
  }

  @Provides
  fun provideApplication(myApplication: MyApplication): Application {
    return myApplication
  }

}