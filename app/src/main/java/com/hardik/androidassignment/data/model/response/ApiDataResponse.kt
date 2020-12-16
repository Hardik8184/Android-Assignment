package com.hardik.androidassignment.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ApiDataResponse : Serializable {

  @SerializedName("title")
  var title: String? = null

  @SerializedName("rows")
  var rows: ArrayList<RowsResponseData>? = null

  class RowsResponseData : Serializable {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("imageHref")
    var imageHref: String? = null
  }
}