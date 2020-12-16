package com.hardik.androidassignment.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hardik.androidassignment.R
import com.google.android.material.snackbar.Snackbar

/**
 * Created by admin on 25/01/18.
 */

@Suppress("DEPRECATION")
@SuppressLint("StaticFieldLeak")
object CommonDataUtility {

  @JvmStatic
  @BindingAdapter("isGone")
  fun bindViewVisibility(
    view: View,
    isGone: Boolean
  ) {
    view.visibility = if (isGone) {
      View.GONE
    } else {
      View.VISIBLE
    }
  }

  @JvmStatic
  @BindingAdapter("android:imageUrl")
  fun loadImage(
    view: ImageView,
    imageUrl: String
  ) {

    Glide.with(view.context)
        .load(imageUrl)
        .override(600, 200)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(
            RequestOptions().placeholder(R.drawable.missing_logo).error(
                R.drawable.missing_logo
            )
        )
        .into(view)
  }

  fun checkConnection(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
  }

  fun showLog(
    type: String,
    logMessage: String
  ) {
    println(StaticDataUtility.APP_TAG + " : " + type + " --> " + logMessage)
  }

  //<editor-fold desc="SnackBar">

  fun showErrorSnackBar(
    activity: Activity,
    view: View,
    message: String
  ) {

    val snackBar: Snackbar =
        Snackbar.make(view, message,
            Snackbar.LENGTH_LONG)
    snackBar.setActionTextColor(Color.WHITE)
    val sbView = snackBar.view
    sbView.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary))
    val textView = sbView.findViewById<View>(
        com.google.android.material.R.id.snackbar_text) as TextView
    textView.setTextColor(Color.WHITE)
    snackBar.show()

//    val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
//    val snackBarView = snackBar.view
//    snackBarView.setBackgroundColor(
//        ContextCompat.getColor(
//            activity, R.color
//            .colorSnackBarNegative
//        )
//    )
//    val textView = snackBarView.findViewById<TextView>(R.id.snackbar_text)
//    textView.setTextColor(Color.WHITE)
//    snackBar.show()
  }

//  fun showSuccessSnackBar(
//    activity: Activity,
//    view: View,
//    message: String
//  ) {
//    val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
//    val snackBarView = snackBar.view
//    snackBarView.setBackgroundColor(
//        ContextCompat.getColor(
//            activity, R.color
//            .colorSnackBarPositive
//        )
//    )
//    val textView = snackBarView.findViewById<TextView>(R.id.snackbar_text)
//    textView.setTextColor(Color.WHITE)
//    snackBar.show()
//  }

  //</editor-fold>
}