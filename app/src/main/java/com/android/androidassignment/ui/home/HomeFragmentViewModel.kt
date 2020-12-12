package com.android.androidassignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.androidassignment.data.api.APIService
import com.android.androidassignment.data.model.MyError
import com.hardik.mvvmapp.data.model.response.ApiDataResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
  private val apiService: APIService,
  private val compositeDisposable: CompositeDisposable
) : ViewModel() {

  private val _countryResponseLiveData: MutableLiveData<ArrayList<ApiDataResponse.RowsResponseData>> =
      MutableLiveData()
  val countryResponseLiveData: LiveData<ArrayList<ApiDataResponse.RowsResponseData>>
    get() = _countryResponseLiveData

  private val _showProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)
  val showProgressBar: LiveData<Boolean>
    get() = _showProgressBar

  private val _errorResponse: MutableLiveData<String> = MutableLiveData("")
  val errorResponse: LiveData<String>
    get() = _errorResponse

  fun getData() {

    _showProgressBar.value = true

    compositeDisposable.add(apiService.getCountryData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { response ->

              _showProgressBar.value = false

              if (response.rows!!.size > 0) {
                _countryResponseLiveData.value = response.rows!!
              } else {
                _errorResponse.value = ""
              }
            },
            { error ->
              _showProgressBar.value = false
              _errorResponse.value = MyError(error).getMessage()
            }
        ))
  }

  override fun onCleared() {
    compositeDisposable.dispose()
    super.onCleared()
  }
}