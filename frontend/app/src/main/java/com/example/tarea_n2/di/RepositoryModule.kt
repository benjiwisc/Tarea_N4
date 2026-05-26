package com.example.tarea_n2.di

import com.example.tarea_n2.data.repository.category.CategoryRepository
import com.example.tarea_n2.data.repository.category.CategoryRepositoryImpl
import com.example.tarea_n2.data.repository.event.EventRepository
import com.example.tarea_n2.data.repository.event.EventRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindEventRepository(
        eventRepositoryImpl: EventRepositoryImpl
    ): EventRepository
}
