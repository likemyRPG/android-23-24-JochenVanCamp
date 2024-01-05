package com.example.nameanalysis.data.network

import retrofit2.Response

/**
 * A generic function for making safe API calls.
 *
 * This function aims to simplify error handling when making network requests. It executes a given network
 * call and handles any exceptions that may occur, returning a standardized [ApiResponse].
 *
 * @param T The type of the response expected from the API call.
 * @param call A suspending lambda function that makes the network request and returns a [Response]<T>.
 * @return [ApiResponse]<T> The result of the API call, encapsulated in either [ApiResponse.Success] if the
 *         request is successful, or [ApiResponse.Error] if the request fails or an exception occurs.
 */
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
