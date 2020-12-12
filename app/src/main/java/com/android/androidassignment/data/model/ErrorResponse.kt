package com.android.androidassignment.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
  @SerializedName("code") val errorCode: Int?,
  @SerializedName("msg") val errorMessage: String?
)