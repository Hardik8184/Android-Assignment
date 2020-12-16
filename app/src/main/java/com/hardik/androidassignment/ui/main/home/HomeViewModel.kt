package com.hardik.androidassignment.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hardik.androidassignment.data.api.ApiRepository
import com.hardik.androidassignment.data.model.local.DataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  private val apiRepository: ApiRepository,
  private val compositeDisposable: CompositeDisposable
) : ViewModel() {

  private val _titleData: MutableLiveData<String> = MutableLiveData()
  val titleData: LiveData<String>
    get() = _titleData

  private val _dataList: MutableLiveData<ArrayList<DataModel>> =
      MutableLiveData(ArrayList())
  val dataList: LiveData<ArrayList<DataModel>>
    get() = _dataList

  private val _showProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)
  val showProgressBar: LiveData<Boolean>
    get() = _showProgressBar

  private val _errorResponse: MutableLiveData<String> = MutableLiveData("")
  val errorResponse: LiveData<String>
    get() = _errorResponse

  fun getData() {

    _showProgressBar.value = true

    compositeDisposable.add(apiRepository.getCountryData()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { response ->

              _showProgressBar.value = false

              if (response.rows!!.size > 0) {

                val list = ArrayList<DataModel>()

                try {

                  for (i in 0 until response.rows!!.size) {

                    if (response.rows!![i].title != null) {
                      list.add(
                          DataModel(
                              response.rows!![i].title!!,
                              if (response.rows!![i].description == null) "Description Not Available" else response.rows!![i].description!!,
                              if (response.rows!![i].imageHref == null) "" else response.rows!![i].imageHref!!,
                          )
                      )
                    }

                  }

                  _dataList.value = list
                  // _titleData.value = response.title!!

                } catch (e: Exception) {
                  println("Hardik error --> " + e.localizedMessage)
                }

              } else {
                _errorResponse.value = "No record found"
              }
            },
            { error ->
              _showProgressBar.value = false
              _errorResponse.value = error.localizedMessage
            }
        ))
  }

  override fun onCleared() {
    compositeDisposable.dispose()
    super.onCleared()
  }
}