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

@RunWith(AndroidJUnit4::class)

class DbGenderAnalysisDAOTest {
    private lateinit var genderAnalysisDao: GenderAnalysisDao
    private lateinit var db: AppDatabase

    private val genderAnalysis1 = GenderAnalysis("John", "male", 0.99, 100)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        genderAnalysisDao = db.genderAnalysisDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetGenderAnalysis() = runBlocking {
        genderAnalysisDao.insertGenderAnalysis(genderAnalysis1)
        val retrieved = genderAnalysisDao.getGenderAnalysis("John")
        assertEquals(retrieved, genderAnalysis1)
    }

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