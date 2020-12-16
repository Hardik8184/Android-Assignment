package com.hardik.androidassignment.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.hardik.androidassignment.di.scope.ActivityContext
import com.hardik.androidassignment.ui.BaseActivity
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
    val compositeDisposible: CompositeDisposable
        get() = CompositeDisposable()

}