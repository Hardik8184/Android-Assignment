package com.hardik.androidassignment.di.module

import com.hardik.androidassignment.BuildConfig
import com.hardik.androidassignment.di.scope.ApplicationScope
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
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    // FastamyLogger.d("Okhttp", message)
                }
            })
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
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