package com.example.nameanalysis.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data Access Object (DAO) for the [GenderAnalysis] entity.
 *
 * This interface defines the methods for accessing the gender analysis data stored in the database.
 * It includes operations for retrieving and inserting gender analysis records.
 */
@Dao
interface GenderAnalysisDao {
    /**
     * Retrieves a [GenderAnalysis] record by name.
     *
     * This method queries the database for a gender analysis record matching the provided name.
     * The query is case-insensitive. If no record is found, the method returns null.
     *
     * @param name The name to query for in the database.
     * @return [GenderAnalysis]? The gender analysis record matching the name, or null if not found.
     */
    @Query("SELECT * FROM GenderAnalysis WHERE LOWER(name) = LOWER(:name)")
    suspend fun getGenderAnalysis(name: String): GenderAnalysis?

    /**
     * Inserts a [GenderAnalysis] record into the database.
     *
     * If a record with the same primary key (name) already exists, it will be replaced.
     * This method is useful for keeping the database up-to-date with the latest analysis.
     *
     * @param genderAnalysis The [GenderAnalysis] record to be inserted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenderAnalysis(genderAnalysis: GenderAnalysis)
}
