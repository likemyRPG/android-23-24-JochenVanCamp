package com.example.nameanalysis.data.repository

import com.example.nameanalysis.data.database.AppDatabase
import com.example.nameanalysis.data.database.GenderAnalysis
import com.example.nameanalysis.data.model.GenderResponse
import com.example.nameanalysis.data.network.ApiResponse
import com.example.nameanalysis.data.network.RetrofitBuilder
import com.example.nameanalysis.data.network.safeApiCall

/**
 * Interface defining the repository operations for name analysis.
 *
 * This interface outlines the methods required for fetching gender analysis data,
 * which can be implemented by various data sources.
 */
interface NameAnalysisRepository {
    /**
     * Fetches gender analysis data for a given name.
     *
     * @param name The name to analyze.
     * @return [ApiResponse]<[GenderResponse]> The response containing gender analysis data.
     */
    suspend fun getGender(name: String): ApiResponse<GenderResponse>
}

/**
 * Implementation of [NameAnalysisRepository] that interacts with both local database and network.
 *
 * This class provides a concrete implementation of [NameAnalysisRepository] and handles data
 * fetching logic, including checking the local database first and then fetching from the network if necessary.
 *
 * @param database The Room database instance for local data access.
 */
class NameAnalysisRepositoryImpl(database: AppDatabase) : NameAnalysisRepository {
    private val genderizeDao = database.genderAnalysisDao()

    /**
     * Fetches gender analysis data, first trying the local database, then the network if not found.
     *
     * @param name The name to analyze.
     * @return [ApiResponse]<[GenderResponse]> The response containing gender analysis data.
     */
    override suspend fun getGender(name: String): ApiResponse<GenderResponse> {
        val localData = genderizeDao.getGenderAnalysis(name)
        return localData?.let {
            ApiResponse.Success(it.toResponse())
        } ?: fetchAndStoreGender(name)
    }

    /**
     * Fetches gender analysis data from the network and stores it in the local database.
     *
     * This method fetches data using the network service and upon success, stores it in the local database
     * for future queries.
     *
     * @param name The name to analyze.
     * @return [ApiResponse]<[GenderResponse]> The response containing the fetched gender analysis data.
     */
    private suspend fun fetchAndStoreGender(name: String): ApiResponse<GenderResponse> {
        return when (val apiResult = safeApiCall { RetrofitBuilder.genderizeService.getGender(name) }) {
            is ApiResponse.Success -> {
                val response = apiResult.data
                genderizeDao.insertGenderAnalysis(response.toEntity(name))
                ApiResponse.Success(response)
            }
            is ApiResponse.Error -> apiResult
        }
    }

    /**
     * Converts a [GenderResponse] to a [GenderAnalysis] entity.
     *
     * This extension function converts the response model to an entity suitable for storing in the local database.
     *
     * @param name The name associated with the gender analysis.
     * @return [GenderAnalysis] The converted entity for local database storage.
     */
    private fun GenderResponse.toEntity(name: String) = GenderAnalysis(
        name = name,
        gender = this.gender ?: "unknown",
        probability = this.probability,
        count = this.count
    )
}
