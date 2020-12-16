package com.hardik.androidassignment.data.api

import com.hardik.androidassignment.data.model.response.ApiDataResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiCalls {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getCountryData(): Single<ApiDataResponse>
}