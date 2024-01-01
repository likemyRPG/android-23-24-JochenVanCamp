package com.example.nameanalysis.data.repository

import com.example.nameanalysis.data.network.RetrofitBuilder

class NameAnalysisRepository {

    suspend fun getNationality(name: String) = RetrofitBuilder.nationalizeService.getNationality(name)

    suspend fun getGender(name: String) = RetrofitBuilder.genderizeService.getGender(name)
}
