package com.android.androidassignment.data.model

import com.android.androidassignment.utils.ServerException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class MyError private constructor(throwable: Throwable?, response: ErrorResponse?) {

  constructor(throwable: Throwable?) : this(throwable, null)

  constructor(errorResponse: ErrorResponse?) : this(null, errorResponse)

  private var message: String = "Something went Wrong"
  private var code: Int = 0

  init {
    if (throwable != null) {
      when (throwable) {
        is HttpException -> {
          message = throwable.message()
          code = throwable.code()
          // message = throwable.response()!!.errorBody()
        }
        is SocketTimeoutException -> {
          message = throwable.message.orEmpty()
        }
        is IOException -> {
          message = throwable.message.orEmpty()
        }
        is ServerException -> {
          message = throwable.message
          code = throwable.code
        }
        else -> {
          message = throwable.message.orEmpty()
        }
      }
    }
    if (response != null) {
      message = response.errorMessage.orEmpty()
      code = response.errorCode ?: 0
    }
  }

  fun getMessage(): String {
    return message
  }

  fun getCode(): Int {
    return code
  }

}