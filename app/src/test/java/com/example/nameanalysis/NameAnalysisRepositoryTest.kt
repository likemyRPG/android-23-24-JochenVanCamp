package com.example.nameanalysis

import com.example.nameanalysis.data.database.AppDatabase
import com.example.nameanalysis.data.database.GenderAnalysis
import com.example.nameanalysis.data.database.GenderAnalysisDao
import com.example.nameanalysis.data.model.GenderResponse
import com.example.nameanalysis.data.network.ApiResponse
import com.example.nameanalysis.data.repository.NameAnalysisRepository
import org.junit.Test
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NameAnalysisRepositoryTest {
    @Mock
    private lateinit var mockDatabase: AppDatabase
    @Mock
    private lateinit var mockDao: GenderAnalysisDao
    private lateinit var repository: NameAnalysisRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        whenever(mockDatabase.genderAnalysisDao()).thenReturn(mockDao)
        repository = NameAnalysisRepository(mockDatabase)
    }

    @Test
    fun getGender_returnsExpectedGender() = runBlocking {
        val testName = "Alex"
        val genderAnalysis = GenderAnalysis(testName, "male", 0.99, 100)
        val expectedResponse = GenderResponse(testName, "male", 0.99, 100)

        whenever(mockDao.getGenderAnalysis(testName)).thenReturn(genderAnalysis)

        when (val result = repository.getGender(testName)) {
            is ApiResponse.Success -> assertEquals(expectedResponse, result.data)
            else -> fail("Expected ApiResponse.Success but got ${result::class}")
        }
    }
}