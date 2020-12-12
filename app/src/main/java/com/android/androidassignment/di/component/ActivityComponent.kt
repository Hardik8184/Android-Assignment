package com.android.androidassignment.di.component

import com.android.androidassignment.ui.main.MainActivity
import com.android.androidassignment.di.module.ActivityModule
import com.android.androidassignment.di.scope.ActivityScope
import com.android.androidassignment.ui.BaseActivity
import com.android.androidassignment.ui.home.HomeFragment
import com.android.androidassignment.ui.splash.SplashScreenActivity
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