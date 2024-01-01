package com.example.nameanalysis.data.network

import com.example.nameanalysis.data.model.GenderizeResponse
import com.example.nameanalysis.data.model.NationalizeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getNationality(@Query("name") name: String): NationalizeResponse

    @GET("/")
    suspend fun getGender(@Query("name") name: String): GenderizeResponse
}
