package com.example.nameanalysis.data.model

import com.squareup.moshi.Json

data class NationalizeResponse(
    val country: List<CountryProbability>
)

data class CountryProbability(
    val country_id: String,
    val probability: Double
)

data class GenderizeResponse(
    val gender: String,
    val probability: Double,
    val count: Int
)
