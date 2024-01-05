package com.example.nameanalysis

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nameanalysis.data.database.AppDatabase
import com.example.nameanalysis.data.database.GenderAnalysis
import com.example.nameanalysis.data.database.GenderAnalysisDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * A test suite for the GenderAnalysisDao in the Name Analysis application.
 *
 * This class contains unit tests for the data access object (DAO) operations related to the GenderAnalysis entity.
 * It uses an in-memory database for testing the database interactions. The tests cover insertion and retrieval
 * of GenderAnalysis entities, ensuring the correct functioning of the DAO methods.
 */
@RunWith(AndroidJUnit4::class)
class DbGenderAnalysisDAOTest {
    private lateinit var genderAnalysisDao: GenderAnalysisDao
    private lateinit var db: AppDatabase

    // Sample GenderAnalysis entity for testing
    private val genderAnalysis1 = GenderAnalysis("John", "male", 0.99, 100)

    /**
     * Sets up the in-memory database and DAO instance before each test.
     *
     * This method initializes the Room database in memory and retrieves the DAO for testing.
     */
    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        genderAnalysisDao = db.genderAnalysisDao()
    }

    /**
     * Closes the database after each test.
     *
     * This method ensures that the database is properly closed after each test, to avoid memory leaks.
     */
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    /**
     * Tests the insertion and retrieval of a GenderAnalysis entity.
     *
     * This test checks whether a GenderAnalysis entity can be successfully inserted and then retrieved from the database.
     */
    @Test
    @Throws(Exception::class)
    fun insertAndGetGenderAnalysis() = runBlocking {
        genderAnalysisDao.insertGenderAnalysis(genderAnalysis1)
        val retrieved = genderAnalysisDao.getGenderAnalysis("John")
        assertEquals(retrieved, genderAnalysis1)
    }

    /**
     * Tests the replace on conflict strategy of the DAO.
     *
     * This test verifies that inserting a GenderAnalysis entity with the same primary key (name) as an existing record
     * replaces the old record with the new one, as per the defined conflict strategy.
     */
    @Test
    @Throws(Exception::class)
    fun replaceOnConflict() = runBlocking {
        genderAnalysisDao.insertGenderAnalysis(genderAnalysis1)
        val modifiedGenderAnalysis = GenderAnalysis("John", "unknown", 0.5, 50)
        genderAnalysisDao.insertGenderAnalysis(modifiedGenderAnalysis)
        val retrieved = genderAnalysisDao.getGenderAnalysis("John")
        assertEquals(retrieved, modifiedGenderAnalysis)
    }
}