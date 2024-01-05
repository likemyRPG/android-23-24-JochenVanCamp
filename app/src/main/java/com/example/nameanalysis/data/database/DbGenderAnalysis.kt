package com.example.nameanalysis.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nameanalysis.data.model.GenderResponse

/**
 * A Room entity representing a gender analysis record.
 *
 * This data class stores information about the analysis of names and their associated gender.
 * It includes fields for the name, the predicted gender, the probability of the prediction,
 * and the count of occurrences used for analysis.
 *
 * @property name The name being analyzed. It serves as the primary key in the database.
 * @property gender The predicted gender for the name. It's nullable to handle cases where the gender is not determined.
 * @property probability The probability score of the gender prediction.
 * @property count The number of occurrences that were analyzed to determine the gender.
 */
@Entity
data class GenderAnalysis(
    @PrimaryKey val name: String,
    val gender: String?,
    val probability: Double,
    val count: Int
) {
    /**
     * Converts the [GenderAnalysis] entity to a [GenderResponse] model.
     *
     * This function is used to map the database entity to a more general model,
     * which can be used throughout the app for various purposes, like network communication.
     *
     * @return [GenderResponse] The converted response model with the same properties as the entity.
     */
    fun toResponse() = GenderResponse(
        name = this.name,
        gender = this.gender,
        probability = this.probability,
        count = this.count
    )
}