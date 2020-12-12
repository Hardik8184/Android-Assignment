/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.androidassignment.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <DATA>
</DATA> */
data class Resource<out DATA, ERROR>(
  val status: Status,
  val data: DATA?,
  val error: ERROR?
) {
  companion object {
    fun <DATA, ERROR> success(data: DATA?, error: ERROR?): Resource<DATA, ERROR> {
      return Resource(Status.SUCCESS, data, null)
    }

    fun <DATA, ERROR> error(data: DATA?, error: ERROR?): Resource<DATA, ERROR> {
      return Resource(Status.ERROR, null, error)
    }

    fun <DATA, ERROR> loading(data: DATA?, error: ERROR?): Resource<DATA, ERROR> {
      return Resource(Status.LOADING, data, null)
    }
  }
}
