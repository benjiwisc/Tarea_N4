package com.example.tarea_n2.data.repository

import android.content.Context
import com.example.tarea_n2.data.local.AppDatabase
import com.example.tarea_n2.data.repository.category.CategoryRepository
import com.example.tarea_n2.data.repository.category.CategoryRepositoryImpl
import com.example.tarea_n2.data.repository.event.EventRepository
import com.example.tarea_n2.data.repository.event.EventRepositoryImpl

interface AppContainer {
    val categoryRepository: CategoryRepository
    val eventRepository: EventRepository
}

class AppDataContainer(
    private val context: Context
) : AppContainer {

    override val categoryRepository: CategoryRepository by lazy {
        CategoryRepositoryImpl(
            AppDatabase.getDatabase(context)
        )
    }

    override val eventRepository: EventRepository by lazy {
        EventRepositoryImpl(
            AppDatabase.getDatabase(context)
        )
    }
}
