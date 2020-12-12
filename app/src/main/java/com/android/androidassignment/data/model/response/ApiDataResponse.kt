package com.hardik.mvvmapp.data.model.response

import com.google.gson.annotations.SerializedName

class ApiDataResponse {

  @SerializedName("title")
  var title: String? = null

  @SerializedName("rows")
  var rows: ArrayList<RowsResponseData>? = null

  class RowsResponseData {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("imageHref")
    var imageHref: String? = null
  }
}