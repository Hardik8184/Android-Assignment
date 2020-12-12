package com.android.androidassignment.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.android.androidassignment.utils.kprogresshud.KProgressHUD
abstract class BaseFragment : Fragment() {

  private lateinit var progressHUD: KProgressHUD

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    progressHUD = KProgressHUD.create(activity)
        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        .setCancellable(false)
  }

  fun hideProgressBar() {
    progressHUD.let { if (it.isShowing) it.dismiss() }
  }

  fun showProgressBar() {
    hideProgressBar()
    progressHUD.show()
  }
}