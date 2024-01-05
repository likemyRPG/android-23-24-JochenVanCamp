package com.example.nameanalysis.data.model

/**
 * A data class representing a response for a gender analysis request.
 *
 * This class encapsulates the result of a gender analysis operation, providing details such as
 * the name analyzed, the predicted gender, the probability of the prediction, and the count of
 * occurrences analyzed. It is typically used for network responses or inter-module data transfer.
 *
 * @property name The name that was analyzed.
 * @property gender The predicted gender for the name. This can be null if the gender could not be determined.
 * @property probability The probability score of the gender prediction.
 * @property count The number of occurrences analyzed to predict the gender.
 */
data class GenderResponse(
    val name: String,
    val gender: String?,
    val probability: Double,
    val count: Int
)