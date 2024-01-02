package com.example.nameanalysis.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nameanalysis.data.model.GenderResponse

@Entity
data class GenderAnalysis(
    @PrimaryKey val name: String,
    val gender: String?,
    val probability: Double,
    val count: Int
) {
    fun toResponse() = GenderResponse(
        name = this.name,
        gender = this.gender,
        probability = this.probability,
        count = this.count
    )
}