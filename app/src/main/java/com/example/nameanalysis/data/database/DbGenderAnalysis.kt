package com.example.nameanalysis.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenderAnalysis(
    @PrimaryKey val name: String,
    val gender: String?,
    val probability: Double,
    val count: Int
)