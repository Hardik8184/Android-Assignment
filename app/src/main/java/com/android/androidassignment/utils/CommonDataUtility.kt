package com.hardik.mvvmapp.utils.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.util.Patterns
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.hardik.mvvmapp.R
import java.util.Locale
import java.util.regex.Pattern

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

  /**
   *  Method used to capitalize first letter of every word
   *  @param input word to capitalize
   */
  fun capitalize(input: String): String {

    val words = input.toLowerCase(Locale.getDefault())
        .split(" ".toRegex())
        .dropLastWhile { it.isEmpty() }
        .toTypedArray()
    val builder = StringBuilder()
    for (i in words.indices) {
      val word = words[i]

      if (i > 0 && word.isNotEmpty()) {
        builder.append(" ")
      }

      builder.append(word.substring(0, 1).toUpperCase(Locale.getDefault()) + word.substring(1))
    }
    return builder.toString()
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
            .setAction("OK") { }
    snackBar.setActionTextColor(Color.WHITE)
    val sbView = snackBar.view
    sbView.setBackgroundColor(activity.resources.getColor(R.color.btnBackground))
    val textView = sbView.findViewById<View>(
        com.google.android.material.R.id.snackbar_text) as TextView
//    textView.setTypeface(com.technotech.padosi.utility.Utils.getTypeFace(mainLayout.getContext(),
//        com.technotech.padosi.utility.Utils.TYPEFACE_ONDO_MEDIUM))
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

  fun showSuccessSnackBar(
    activity: Activity,
    view: View,
    message: String
  ) {
    val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    val snackBarView = snackBar.view
    snackBarView.setBackgroundColor(
        ContextCompat.getColor(
            activity, R.color
            .colorSnackBarPositive
        )
    )
    val textView = snackBarView.findViewById<TextView>(R.id.snackbar_text)
    textView.setTextColor(Color.WHITE)
    snackBar.show()
  }

  //</editor-fold>

  fun isValidEmailId(email: String): Boolean {

    return Pattern
        .compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
        )
        .matcher(email)
        .matches()
  }

  fun isValidMobile(phone: String): Boolean {
    return Patterns.PHONE.matcher(phone).matches()
  }
}