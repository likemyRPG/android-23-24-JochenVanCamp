package com.example.nameanalysis.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nameanalysis.data.repository.NameAnalysisRepository

/**
 * Factory for creating instances of [NameAnalysisViewModel].
 *
 * This class implements the [ViewModelProvider.Factory] interface to provide custom instantiation logic
 * for [NameAnalysisViewModel]. It ensures that the [NameAnalysisViewModel] is created with the necessary
 * [NameAnalysisRepository] dependency.
 *
 * @param repository The [NameAnalysisRepository] instance to be passed to the [NameAnalysisViewModel].
 */
class NameAnalysisViewModelFactory(private val repository: NameAnalysisRepository) : ViewModelProvider.Factory {
    /**
     * Creates a new instance of the specified [ViewModel] class.
     *
     * This method checks if the [modelClass] is assignable from [NameAnalysisViewModel] and, if so,
     * returns a new instance of [NameAnalysisViewModel] with the provided repository. If the [modelClass]
     * is not assignable from [NameAnalysisViewModel], it throws an [IllegalArgumentException].
     *
     * @param T The type parameter representing the class of the ViewModel requested.
     * @param modelClass The [Class] object of the ViewModel to be created.
     * @return A new instance of the specified ViewModel class.
     * @throws IllegalArgumentException if the ViewModel class is unknown.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NameAnalysisViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NameAnalysisViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
