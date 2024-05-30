package com.telema.malakisi.domain


sealed class Result<out T>(
    val data: T? = null,
    val throwable: Throwable? = null
) {
    data class Success<T>(val d: T): Result<T>(d)
    data class Error<T>(val t: Throwable, val d: T? = null): Result<T>(d, t)
    data object Loading : Result<Nothing>()
}