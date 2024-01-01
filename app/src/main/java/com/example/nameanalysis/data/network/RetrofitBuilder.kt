package com.example.nameanalysis.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val nationalizeService: ApiService by lazy {
        getRetrofit("https://api.nationalize.io/").create(ApiService::class.java)
    }

    val genderizeService: ApiService by lazy {
        getRetrofit("https://api.genderize.io/").create(ApiService::class.java)
    }
}
