package com.example.nameanalysis.data.network

import retrofit2.Response

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ApiResponse<T> {
    return try {
        val response = call.invoke()
        if (response.isSuccessful) {
            ApiResponse.Success(response.body()!!)
        } else {
            ApiResponse.Error(response.message(), response.code())
        }
    } catch (e: Exception) {
        ApiResponse.Error(e.message ?: "Unknown error", null)
    }
}
