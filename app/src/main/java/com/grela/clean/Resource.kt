package com.grela.clean

import com.grela.clean.Status.SUCCESS
import com.grela.clean.Status.ERROR
import com.grela.clean.Status.LOADING

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val exception: Error?
) {
  companion object {
    fun <T> success(data: T?): Resource<T> {
      return Resource(SUCCESS, data, null)
    }

    fun <T> error(exception: Error): Resource<T> {
      return Resource(ERROR, null, exception)
    }

    fun <T> loading(): Resource<T> {
      return Resource(LOADING, null, null)
    }
  }
}
