package com.example.nameanalysis.data.network

/**
 * A sealed class representing the response from a network operation.
 *
 * This class encapsulates the result of a network request and is generic to support various data types.
 * It has two subclasses, [Success] and [Error], to represent successful and erroneous outcomes respectively.
 *
 * @param T The type of data expected in the response.
 */
sealed class ApiResponse<T> {
    /**
     * Represents a successful network response.
     *
     * This class contains the successful result data of the network request.
     *
     * @param T The type of the successful response data.
     * @property data The successful data returned from the network request.
     */
    class Success<T>(val data: T) : ApiResponse<T>()
    /**
     * Represents an error in the network response.
     *
     * This class contains details about the error occurred during the network request.
     * It includes an error message and an optional error code.
     *
     * @param T The type of the response expected (irrespective of the error).
     * @property errorMessage A descriptive message about the error.
     * @property errorCode An optional error code associated with the error, can be null.
     */
    class Error<T>(val errorMessage: String, val errorCode: Int? = null) : ApiResponse<T>()
}