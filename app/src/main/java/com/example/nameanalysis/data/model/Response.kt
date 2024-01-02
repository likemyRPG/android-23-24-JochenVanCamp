package com.example.nameanalysis.data.model

data class GenderResponse(
    val name: String,
    val gender: String?,
    val probability: Double,
    val count: Int
)