package com.example.nameanalysis.data

import android.content.Context
import com.example.nameanalysis.data.database.AppDatabase
import com.example.nameanalysis.data.repository.NameAnalysisRepository

interface AppContainer {
    val nameAnalysisRepository: NameAnalysisRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }

    override val nameAnalysisRepository: NameAnalysisRepository by lazy {
        NameAnalysisRepository(database)
    }
}
