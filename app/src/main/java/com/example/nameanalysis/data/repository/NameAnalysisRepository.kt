package com.example.nameanalysis.data.repository

import com.example.nameanalysis.data.database.AppDatabase
import com.example.nameanalysis.data.database.GenderAnalysis
import com.example.nameanalysis.data.model.GenderResponse
import com.example.nameanalysis.data.network.ApiResponse
import com.example.nameanalysis.data.network.RetrofitBuilder
import com.example.nameanalysis.data.network.safeApiCall

interface NameAnalysisRepository {
    suspend fun getGender(name: String): ApiResponse<GenderResponse>
}
class NameAnalysisRepositoryImpl(database: AppDatabase) : NameAnalysisRepository {
    private val genderizeDao = database.genderAnalysisDao()

    override suspend fun getGender(name: String): ApiResponse<GenderResponse> {
        val localData = genderizeDao.getGenderAnalysis(name)
        return localData?.let {
            ApiResponse.Success(it.toResponse())
        } ?: fetchAndStoreGender(name)
    }

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

    private fun GenderResponse.toEntity(name: String) = GenderAnalysis(
        name = name,
        gender = this.gender ?: "unknown",
        probability = this.probability,
        count = this.count
    )
}
