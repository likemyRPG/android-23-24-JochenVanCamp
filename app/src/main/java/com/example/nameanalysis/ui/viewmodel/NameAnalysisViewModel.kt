package com.example.nameanalysis.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nameanalysis.data.model.GenderResponse
import com.example.nameanalysis.data.repository.NameAnalysisRepository

class NameAnalysisViewModel(private val repository: NameAnalysisRepository) : ViewModel() {
    suspend fun getGender(name: String): GenderResponse {
        return repository.getGender(name)
    }
}