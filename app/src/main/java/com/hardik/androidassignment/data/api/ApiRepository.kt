package com.hardik.androidassignment.data.api

import com.hardik.androidassignment.data.model.response.ApiDataResponse
import io.reactivex.Single
import javax.inject.Inject

class ApiRepository @Inject constructor(
  private val apiRemote: ApiRemote
) {

  fun getCountryData(): Single<ApiDataResponse> {
    return apiRemote.getCountryData()
  }

}