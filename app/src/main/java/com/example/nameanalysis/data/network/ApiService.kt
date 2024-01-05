package com.example.nameanalysis.data.network

import com.example.nameanalysis.data.model.GenderResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * An interface defining the API service for gender analysis.
 *
 * This interface is used with Retrofit to define the HTTP operations. It includes methods to interact
 * with the network API for fetching gender analysis data.
 */
interface ApiService {
    /**
     * Fetches gender analysis data for a given name.
     *
     * This method sends a GET request to the server. It takes a name as a query parameter and
     * returns a [Response] wrapping [GenderResponse], which contains the analysis result.
     * This method is a suspending function, suitable for use in a coroutine context.
     *
     * @param name The name for which gender analysis is requested.
     * @return [Response]<[GenderResponse]> The response from the server, encapsulating the gender analysis result.
     */
    @GET("/")
    suspend fun getGender(@Query("name") name: String): Response<GenderResponse>
}
