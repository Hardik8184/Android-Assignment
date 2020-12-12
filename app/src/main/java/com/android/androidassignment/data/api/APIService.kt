package com.android.androidassignment.data.api

import com.hardik.mvvmapp.data.model.response.ApiDataResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url
import javax.inject.Singleton

@Singleton
interface APIService {

  @GET("s/2iodh4vg0eortkl/facts.json")
  fun getCountryData(): Single<ApiDataResponse>
}