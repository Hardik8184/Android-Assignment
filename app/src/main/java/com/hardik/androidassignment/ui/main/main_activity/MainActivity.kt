package com.hardik.androidassignment.ui.main.main_activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.postDelayed
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hardik.androidassignment.R
import com.hardik.androidassignment.databinding.ActivityMainBinding
import com.hardik.androidassignment.ui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  lateinit var binding: ActivityMainBinding

  private val mainActivityViewModel: MainActivityViewModel by viewModels {
    viewModelFactory
  }

  private lateinit var navController: NavController
  private var backPressedOnce = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    getActivityComponent().inject(this)

    createDatabase()

    setDataBinding()
    setToolbar()
  }

  override fun onBackPressed() {
    if (navController.graph.startDestination == navController.currentDestination?.id) {
      if (backPressedOnce) {
        super.onBackPressed()
        return
      }

      backPressedOnce = true
      Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT)
          .show()

      Handler(Looper.getMainLooper()).postDelayed(2000) {
        backPressedOnce = false
      }

    } else {
      super.onBackPressed()
    }
  }

  /* Set toolbar title */
  private fun setToolbar() {

    // Set up ActionBar
//    setSupportActionBar(binding.toolbar)
//    supportActionBar!!.title = ""
  }

  /* Set Data Binding */
  private fun setDataBinding() {

    binding =
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    binding.lifecycleOwner = this

    navController = Navigation.findNavController(this, R.id.fragNavHost)
  }

  fun getToolBarTitle(): TextView {
    return binding.txtTitle
  }
}