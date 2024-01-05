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

/**
 * A test suite for verifying the behavior of [NameAnalysisViewModel] with a mocked [NameAnalysisRepository].
 *
 * This class tests the interaction between the ViewModel and the repository. It uses mock objects and LiveData
 * observation to verify the ViewModel's response to successful and failed data fetch operations.
 */
@ExperimentalCoroutinesApi
class NameAnalysisRepositoryTest {

    /**
     * Rule to execute LiveData operations in the same thread for immediate results.
     */
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    /**
     * Rule for setting a test dispatcher for coroutines.
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * Mock instance of [NameAnalysisRepository] for simulating repository operations.
     */
    @Mock
    private lateinit var repository: NameAnalysisRepository

    /**
     * Instance of [NameAnalysisViewModel] under test.
     */
    private lateinit var viewModel: NameAnalysisViewModel

    /**
     * Initializes mock objects and the ViewModel before each test.
     */
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = NameAnalysisViewModel(repository)
    }

    /**
     * Tests if the ViewModel updates its genderResponse LiveData with the correct data
     * when the repository returns a successful response.
     */
    @Test
    fun testGetGenderSuccessUpdatesGenderResponse() = runTest {
        // Mock setup, LiveData observation, and assertions
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

    /**
     * Tests if the ViewModel updates its errorMessage LiveData with the correct message
     * when the repository returns an error response.
     */
    @Test
    fun testGetGenderFailureUpdatesErrorMessage() = runTest {
        // Mock setup, LiveData observation, and assertions
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