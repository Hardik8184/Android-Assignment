package com.android.androidassignment.utils

import com.android.androidassignment.data.model.ErrorResponse

class ServerException(
    private val errorResponse: ErrorResponse?,
    error: Throwable? = null
) : Exception() {
    override var message: String = errorResponse?.errorMessage.orEmpty()
    val code: Int = errorResponse?.errorCode ?: 0

}