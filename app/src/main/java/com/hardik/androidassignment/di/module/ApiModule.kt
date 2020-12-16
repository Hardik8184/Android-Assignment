package com.hardik.androidassignment.di.module

import com.hardik.androidassignment.data.api.ApiCalls
import com.hardik.androidassignment.di.scope.ApplicationScope
import com.hardik.mvvmapp.utils.helper.StaticDataUtility
import dagger.Module
import dagger.Provides
//import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module(includes = [Network::class])
class
ApiModule {

  @Provides
  @ApplicationScope
  fun userToUserVerificationCalls(retrofit: Retrofit): ApiCalls {
    return retrofit.create(ApiCalls::class.java)
  }

  @Provides
  @ApplicationScope
  fun retrofitClient(
    client: OkHttpClient,
    gsonConverterFactory: Converter.Factory,
    rxCallAdapter: CallAdapter.Factory,
    @Named("baseurl") baseUrl: String
  ): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxCallAdapter)
        .client(client)
        .baseUrl(baseUrl)
        .build()
  }

  @get:ApplicationScope
  @get:Provides
  val gsonConvertorFactory: Converter.Factory
    get() = GsonConverterFactory.create()

  @get:ApplicationScope
  @get:Provides
  val rxCallAdapterFactory: CallAdapter.Factory
    get() = RxJava2CallAdapterFactory.create()

  @get:ApplicationScope
  @get:Provides
  @get:Named("baseurl")
  val baseUrl: String
    get() = StaticDataUtility.SERVER_URL
}