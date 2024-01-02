package com.example.nameanalysis.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nameanalysis.data.model.GenderResponse
import com.example.nameanalysis.data.network.ApiResponse
import com.example.nameanalysis.data.repository.NameAnalysisRepository
import kotlinx.coroutines.launch

class NameAnalysisViewModel(private val repository: NameAnalysisRepository) : ViewModel() {
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _genderResponse = MutableLiveData<GenderResponse?>()
    val genderResponse: MutableLiveData<GenderResponse?> = _genderResponse

    fun clearGenderResponse() {
        _genderResponse.value = null
    }

    fun getGender(name: String) {
        viewModelScope.launch {
            when (val response = repository.getGender(name)) {
                is ApiResponse.Success -> {
                    _genderResponse.value = response.data
                }
                is ApiResponse.Error -> {
                    println("Error: ${response.errorMessage}")
                    _errorMessage.value = response.errorMessage
                }
            }
        }
    }
}
