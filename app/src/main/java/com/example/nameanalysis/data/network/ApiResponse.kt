package com.example.nameanalysis.data.network

sealed class ApiResponse<T> {
    class Success<T>(val data: T) : ApiResponse<T>()
    class Error<T>(val errorMessage: String, val errorCode: Int? = null) : ApiResponse<T>()
}