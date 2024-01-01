package com.example.nameanalysis.data.network

import com.example.nameanalysis.data.model.GenderResponse
import com.example.nameanalysis.data.model.NationalityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getNationality(@Query("name") name: String): NationalityResponse

    @GET("/")
    suspend fun getGender(@Query("name") name: String): GenderResponse
}
