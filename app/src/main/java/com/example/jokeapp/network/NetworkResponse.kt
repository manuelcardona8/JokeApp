package com.example.jokeapp.network

sealed class NetworkResponse<T> {
    data class Success<T>(val data: List<T>): NetworkResponse<T>()
    data class Error<T>(val error: Throwable): NetworkResponse<T>()
    class Loading<T> : NetworkResponse<T>()
}