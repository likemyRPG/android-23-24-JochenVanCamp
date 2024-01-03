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

@ExperimentalCoroutinesApi

class AppViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Mock
    private lateinit var repository: NameAnalysisRepository

    private lateinit var viewModel: NameAnalysisViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = NameAnalysisViewModel(repository)
        Dispatchers.setMain(testDispatcher.testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getGender_SuccessfulResponse_UpdatesGenderResponseLiveData() = runTest {
        val mockResponse = GenderResponse("John", "male", 0.99, 100)
        whenever(repository.getGender("John")).thenReturn(ApiResponse.Success(mockResponse))

        viewModel.getGender("John")

        Assert.assertEquals(mockResponse, viewModel.genderResponse.value)
    }

    @Test
    fun getGender_ErrorResponse_UpdatesErrorMessageLiveData() = runTest {
        val errorMessage = "Network Error"
        whenever(repository.getGender("Unknown")).thenReturn(ApiResponse.Error(errorMessage))

        viewModel.getGender("Unknown")

        Assert.assertEquals(errorMessage, viewModel.errorMessage.value)
    }

    @Test
    fun getGender_Always_UpdatesIsLoadingLiveData() = runTest {
        whenever(repository.getGender("John")).thenReturn(ApiResponse.Success(GenderResponse("John", "male", 0.99, 100)))

        viewModel.getGender("John")

        Assert.assertFalse(viewModel.isLoading.value!!)
    }

    @Test
    fun clearGenderResponse_SetsGenderResponseToNull() {
        viewModel.clearGenderResponse()

        Assert.assertNull(viewModel.genderResponse.value)
    }
}