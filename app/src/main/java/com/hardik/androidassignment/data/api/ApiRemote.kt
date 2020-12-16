package com.hardik.androidassignment.data.api

import com.hardik.androidassignment.data.model.response.ApiDataResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiRemote @Inject constructor(private val apiCalls: ApiCalls) {

  fun getCountryData(): Single<ApiDataResponse> {
    return apiCalls.getCountryData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
  }
}
