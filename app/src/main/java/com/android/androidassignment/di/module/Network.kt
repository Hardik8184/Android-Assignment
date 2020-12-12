package com.android.androidassignment.di.module

import com.android.androidassignment.di.scope.ApplicationScope
import com.hardik.dssp.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

@Module
class Network {

  @Provides
  @ApplicationScope
  fun loggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor =
        HttpLoggingInterceptor { message -> println("Okhttp -> $message") }
    httpLoggingInterceptor.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return httpLoggingInterceptor
  }

  @Provides
  @ApplicationScope
  fun okHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
          val newRequest: Request
          val requestBuilder = chain.request().newBuilder()
          newRequest = requestBuilder.build()
          chain.proceed(newRequest)
        }

    if (BuildConfig.DEBUG) {
      okHttpClient.addInterceptor(httpLoggingInterceptor)
    }

    return okHttpClient.build()
  }
}