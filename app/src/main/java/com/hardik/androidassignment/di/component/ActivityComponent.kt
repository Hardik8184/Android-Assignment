package com.hardik.androidassignment.di.component

import com.hardik.androidassignment.di.module.ActivityModule
import com.hardik.androidassignment.di.scope.ActivityScope
import com.hardik.androidassignment.ui.BaseActivity
import com.hardik.androidassignment.ui.main.home.HomeFragment
import com.hardik.androidassignment.ui.main.main_activity.MainActivity
import com.hardik.androidassignment.ui.main.splashscreen.SplashScreenActivity
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