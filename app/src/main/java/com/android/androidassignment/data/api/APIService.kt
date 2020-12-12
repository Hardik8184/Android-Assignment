package com.android.androidassignment.data.api

import com.hardik.mvvmapp.data.model.response.ApiDataResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url
import javax.inject.Singleton

@Singleton
interface APIService {

  @GET
  fun getData(@Url url: String): Observable<ApiDataResponse>
}