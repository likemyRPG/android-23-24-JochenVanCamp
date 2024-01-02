package com.example.nameanalysis.data.repository

import com.example.nameanalysis.data.database.AppDatabase
import com.example.nameanalysis.data.database.GenderAnalysis
import com.example.nameanalysis.data.model.GenderResponse
import com.example.nameanalysis.data.network.RetrofitBuilder

class NameAnalysisRepository(private val database: AppDatabase) {

    private val genderizeDao = database.genderAnalysisDao()

    suspend fun getGender(name: String): GenderResponse {
        val localData = genderizeDao.getGenderAnalysis(name)
        return localData?.toResponse() ?: fetchAndStoreGender(name)
    }

    private suspend fun fetchAndStoreGender(name: String): GenderResponse {
        val response = RetrofitBuilder.genderizeService.getGender(name)
        val entity = response.toEntity(name)
        genderizeDao.insertGenderAnalysis(entity)
        return response
    }

    private fun GenderResponse.toEntity(name: String) = GenderAnalysis(
        name = name,
        gender = this.gender ?: "unknown",
        probability = this.probability,
        count = this.count
    )

    private fun GenderAnalysis.toResponse() = GenderResponse(
        name = this.name,
        gender = this.gender,
        probability = this.probability,
        count = this.count
    )
}