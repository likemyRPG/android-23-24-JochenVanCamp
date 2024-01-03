package com.example.nameanalysis

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nameanalysis.data.model.GenderResponse
import com.example.nameanalysis.data.network.ApiResponse
import com.example.nameanalysis.data.repository.NameAnalysisRepository
import com.example.nameanalysis.ui.viewmodel.NameAnalysisViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class NameAnalysisRepositoryTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: NameAnalysisRepository

    private lateinit var viewModel: NameAnalysisViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = NameAnalysisViewModel(repository)
    }

    @Test
    fun testGetGenderSuccessUpdatesGenderResponse() = runTest {
        val testResponse = GenderResponse(name = "John", gender = "male", probability = 0.99, count = 100)
        `when`(repository.getGender("John")).thenReturn(ApiResponse.Success(testResponse))

        val observedValues = mutableListOf<GenderResponse>()
        viewModel.genderResponse.observeForever {
            if (it != null) {
                observedValues.add(it)
            }
        }

        viewModel.getGender("John")
        Assert.assertTrue(observedValues.contains(testResponse))

        viewModel.genderResponse.removeObserver { observedValues.clear() }
        verify(repository).getGender("John")
    }

    @Test
    fun testGetGenderFailureUpdatesErrorMessage() = runTest {
        val errorMessage = "Network Error"
        `when`(repository.getGender("Unknown")).thenReturn(ApiResponse.Error(errorMessage))

        val observedErrors = mutableListOf<String>()
        viewModel.errorMessage.observeForever { observedErrors.add(it) }

        viewModel.getGender("Unknown")
        Assert.assertTrue(observedErrors.contains(errorMessage))

        viewModel.errorMessage.removeObserver { observedErrors.clear() }
        verify(repository).getGender("Unknown")
    }
}