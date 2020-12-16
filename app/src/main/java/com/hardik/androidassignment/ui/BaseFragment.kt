package com.hardik.androidassignment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hardik.androidassignment.ui.main.main_activity.MainActivity

abstract class BaseFragment : Fragment() {

  lateinit var mainActivity: MainActivity

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    mainActivity = activity as MainActivity
  }
}