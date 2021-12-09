package com.example.finaltask2021.common

sealed class DataState<T>(val data: T? = null, val message: String? = null) where T : Any? {
    class Ready<T>(data: T) : DataState<T>(data)
    class Error<T>(message: String) : DataState<T>(message = message)
    class Loading<T> : DataState<T>()
}