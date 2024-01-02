package com.example.nameanalysis.data.network

import com.example.nameanalysis.data.model.GenderResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getGender(@Query("name") name: String): Response<GenderResponse>
}
