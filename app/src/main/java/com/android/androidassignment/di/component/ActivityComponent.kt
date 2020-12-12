package com.android.androidassignment.di.component

import com.android.androidassignment.MainActivity
import com.android.androidassignment.di.module.ActivityModule
import com.android.androidassignment.di.scope.ActivityScope
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun activity(baseActivity: BaseActivity): Builder

        fun build(): ActivityComponent
    }

    fun inject(splashScreenActivity: SplashScreenActivity)
    fun inject(mainActivity: MainActivity)

    fun inject(homeFragment: HomeFragment)
}