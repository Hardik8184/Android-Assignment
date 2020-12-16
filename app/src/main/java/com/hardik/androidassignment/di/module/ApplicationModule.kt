package com.hardik.androidassignment.di.module

import android.app.Application
import android.content.Context
import com.hardik.androidassignment.MyApplication
import com.hardik.androidassignment.di.scope.ApplicationContext
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