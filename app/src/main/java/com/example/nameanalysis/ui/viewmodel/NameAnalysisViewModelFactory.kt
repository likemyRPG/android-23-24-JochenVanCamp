package com.example.nameanalysis.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nameanalysis.data.repository.NameAnalysisRepository

class NameAnalysisViewModelFactory(private val repository: NameAnalysisRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NameAnalysisViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NameAnalysisViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
