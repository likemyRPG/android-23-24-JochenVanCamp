package com.example.nameanalysis

import com.example.nameanalysis.data.model.GenderResponse
import com.example.nameanalysis.data.network.ApiResponse
import com.example.nameanalysis.data.repository.NameAnalysisRepository
import com.example.nameanalysis.ui.viewmodel.NameAnalysisViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

/**
 * A test suite for the [NameAnalysisViewModel] in the Name Analysis application.
 *
 * This class contains unit tests for the ViewModel handling the logic of gender analysis.
 * It uses Mockito for mocking the repository and tests the ViewModel's LiveData properties
 * based on different response scenarios from the repository.
 */
@ExperimentalCoroutinesApi
class AppViewModelTest {

    /**
     * Rule for setting a test dispatcher for coroutines.
     */
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    /**
     * Mock of [NameAnalysisRepository] to simulate data fetching without actual network calls.
     */
    @Mock
    private lateinit var repository: NameAnalysisRepository

    /**
     * Instance of [NameAnalysisViewModel] under test.
     */
    private lateinit var viewModel: NameAnalysisViewModel

    /**
     * Sets up the testing environment for each test.
     *
     * This method initializes Mockito mocks, the ViewModel, and sets a test dispatcher for coroutines.
     */
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = NameAnalysisViewModel(repository)
        Dispatchers.setMain(testDispatcher.testDispatcher)
    }

    /**
     * Resets the coroutine dispatcher after each test.
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Tests if a successful gender analysis response updates the genderResponse LiveData properly.
     */
    @Test
    fun getGender_SuccessfulResponse_UpdatesGenderResponseLiveData() = runTest {
        // Mock setup and LiveData assertion
        val mockResponse = GenderResponse("John", "male", 0.99, 100)
        whenever(repository.getGender("John")).thenReturn(ApiResponse.Success(mockResponse))

        viewModel.getGender("John")

        Assert.assertEquals(mockResponse, viewModel.genderResponse.value)
    }

    /**
     * Tests if an error response from the repository updates the errorMessage LiveData.
     */
    @Test
    fun getGender_ErrorResponse_UpdatesErrorMessageLiveData() = runTest {
        // Mock setup and LiveData assertion
        val errorMessage = "Network Error"
        whenever(repository.getGender("Unknown")).thenReturn(ApiResponse.Error(errorMessage))

        viewModel.getGender("Unknown")

        Assert.assertEquals(errorMessage, viewModel.errorMessage.value)
    }

    /**
     * Tests if isLoading LiveData is updated correctly after fetching gender analysis data.
     */
    @Test
    fun getGender_Always_UpdatesIsLoadingLiveData() = runTest {
        // Mock setup and LiveData assertion
        whenever(repository.getGender("John")).thenReturn(ApiResponse.Success(GenderResponse("John", "male", 0.99, 100)))

        viewModel.getGender("John")

        Assert.assertFalse(viewModel.isLoading.value!!)
    }

    /**
     * Tests if clearGenderResponse sets the genderResponse LiveData to null.
     */
    @Test
    fun clearGenderResponse_SetsGenderResponseToNull() {
        // Action execution and LiveData assertion
        viewModel.clearGenderResponse()

        Assert.assertNull(viewModel.genderResponse.value)
    }
}