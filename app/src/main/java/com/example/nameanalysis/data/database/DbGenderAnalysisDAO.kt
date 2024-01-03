package com.example.nameanalysis.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GenderAnalysisDao {
    @Query("SELECT * FROM GenderAnalysis WHERE LOWER(name) = LOWER(:name)")
    suspend fun getGenderAnalysis(name: String): GenderAnalysis?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenderAnalysis(genderAnalysis: GenderAnalysis)
}
