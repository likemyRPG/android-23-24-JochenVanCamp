package com.example.nameanalysis.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * A singleton object for building Retrofit instances.
 *
 * This object is responsible for creating and providing Retrofit instances with Moshi converters
 * for different base URLs. It ensures the use of the Moshi library for JSON serialization and
 * deserialization, tailored for Kotlin with the KotlinJsonAdapterFactory.
 */
object RetrofitBuilder {
    /**
     * Moshi instance with added [KotlinJsonAdapterFactory] for Kotlin compatibility.
     */
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * Creates and returns a Retrofit instance.
     *
     * This function initializes a new Retrofit builder, sets the base URL for the HTTP requests,
     * adds a Moshi converter factory for JSON serialization, and builds the Retrofit instance.
     *
     * @param baseUrl The base URL for the HTTP requests.
     * @return [Retrofit] The constructed Retrofit instance.
     */
    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    /**
     * Lazy-initialized Retrofit service for the Genderize API.
     *
     * This property provides a Retrofit service for the Genderize API, instantiated only on its first use.
     */
    val genderizeService: ApiService by lazy {
        getRetrofit("https://api.genderize.io/").create(ApiService::class.java)
    }
}
