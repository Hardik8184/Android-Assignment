package com.hardik.androidassignment.di.component

import com.hardik.androidassignment.MyApplication
import com.hardik.androidassignment.di.module.ApiModule
import com.hardik.androidassignment.di.module.ApplicationModule
import com.hardik.androidassignment.di.scope.ApplicationScope
import com.hardik.androidassignment.di.module.ViewModelModule
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