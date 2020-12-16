package com.hardik.androidassignment.ui.main.main_activity

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
  private val compositeDisposable: CompositeDisposable
) : ViewModel() {

  override fun onCleared() {
    compositeDisposable.dispose()
    super.onCleared()
  }
}