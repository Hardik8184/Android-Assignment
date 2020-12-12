package com.android.androidassignment.di.component

import com.android.androidassignment.MyApplication
import com.android.androidassignment.di.module.ApiModule
import com.android.androidassignment.di.module.ApplicationModule
import com.android.androidassignment.di.module.ViewModelModule
import com.android.androidassignment.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ApiModule::class, ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun activityBuilder(): ActivityComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(myApplication: MyApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(myApplication: MyApplication)
}