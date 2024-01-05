package com.example.nameanalysis.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nameanalysis.data.model.GenderResponse
import com.example.nameanalysis.data.network.ApiResponse
import com.example.nameanalysis.data.repository.NameAnalysisRepository
import kotlinx.coroutines.launch

/**
 * ViewModel class for handling name analysis logic and UI data.
 *
 * This ViewModel interacts with the [NameAnalysisRepository] to fetch gender analysis data
 * and manages UI-related data through LiveData. It provides functions for initiating gender analysis
 * and handling the results or errors, along with LiveData objects to observe the state and data changes.
 *
 * @param repository The [NameAnalysisRepository] instance for fetching gender analysis data.
 */
class NameAnalysisViewModel(private val repository: NameAnalysisRepository) : ViewModel() {
    /**
     * LiveData for holding error messages.
     */
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    /**
     * LiveData for holding the gender analysis response.
     */
    private val _genderResponse = MutableLiveData<GenderResponse?>()
    val genderResponse: MutableLiveData<GenderResponse?> = _genderResponse

    /**
     * LiveData for tracking the loading state.
     */
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    /**
     * Clears the gender response data.
     */
    fun clearGenderResponse() {
        _genderResponse.value = null
    }

    /**
     * Initiates the process of fetching gender analysis data.
     *
     * This function updates the loading state, makes a call to the repository to get the gender data,
     * and updates the LiveData with the response or error message.
     *
     * @param name The name to be analyzed for gender prediction.
     */
    fun getGender(name: String) {
        _isLoading.value = true
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
            _isLoading.value = false
        }
    }
}
