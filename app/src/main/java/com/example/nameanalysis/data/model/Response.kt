package com.example.nameanalysis.data.model

data class NationalityResponse(
    val name: String,
    val country: List<CountryProbability>
)

data class CountryProbability(
    val country_id: String,
    val probability: Double
)

data class GenderResponse(
    val name: String,
    val gender: String?,
    val probability: Double,
    val count: Int
)