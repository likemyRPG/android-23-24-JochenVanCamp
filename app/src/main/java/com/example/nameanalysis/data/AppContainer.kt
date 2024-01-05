package com.example.nameanalysis.data

import android.content.Context
import com.example.nameanalysis.data.database.AppDatabase
import com.example.nameanalysis.data.repository.NameAnalysisRepository
import com.example.nameanalysis.data.repository.NameAnalysisRepositoryImpl

/**
 * Interface defining the container for application-wide dependencies.
 *
 * This interface provides access to various repositories and data sources that are required across the application.
 * It ensures that there's a single point of access for these dependencies.
 */
interface AppContainer {
    /**
     * The repository for name analysis operations.
     */
    val nameAnalysisRepository: NameAnalysisRepository
}

/**
 * Implementation of [AppContainer] for managing application-wide dependencies.
 *
 * This class is responsible for initializing and providing dependencies such as databases and repositories.
 * It uses lazy initialization to ensure that these dependencies are created only when needed.
 *
 * @param context The context of the application, used for creating and accessing the database.
 */
class AppDataContainer(private val context: Context) : AppContainer {

    /**
     * Lazy-initialized instance of [AppDatabase].
     *
     * This property holds the instance of the Room database used throughout the application.
     * It is initialized when accessed for the first time.
     */
    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }

    /**
     * Lazy-initialized instance of [NameAnalysisRepository].
     *
     * This property provides access to the name analysis repository, initialized with the local database.
     * It ensures that there is only one instance of the repository throughout the application.
     */
    override val nameAnalysisRepository: NameAnalysisRepository by lazy {
        NameAnalysisRepositoryImpl(database)
    }
}
