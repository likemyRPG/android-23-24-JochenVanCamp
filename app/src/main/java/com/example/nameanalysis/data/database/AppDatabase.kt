package com.example.nameanalysis.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * A Room Database for gender analysis.
 *
 * This abstract class extends [RoomDatabase] and provides an instance of [GenderAnalysisDao].
 * It uses the Singleton pattern to ensure only one instance of the database is created.
 *
 * @property Instance A single instance of [AppDatabase].
 */
@Database(entities = [GenderAnalysis::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Provides the [GenderAnalysisDao] for accessing the database.
     *
     * @return [GenderAnalysisDao] An object for accessing database operations.
     */
    abstract fun genderAnalysisDao(): GenderAnalysisDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null
        /**
         * Gets the single instance of [AppDatabase].
         *
         * If an instance already exists, it returns that instance, otherwise, it creates a new instance.
         * This method is thread-safe.
         *
         * @param context The application context.
         * @return [AppDatabase] The single instance of the database.
         */
        fun getDatabase(context: Context): AppDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
