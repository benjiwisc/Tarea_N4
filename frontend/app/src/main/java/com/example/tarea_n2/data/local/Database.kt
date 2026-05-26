package com.example.tarea_n2.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tarea_n2.data.local.dao.CategoryDao
import com.example.tarea_n2.data.local.dao.EventDao
import com.example.tarea_n2.data.local.entity.CategoryEntity
import com.example.tarea_n2.data.local.entity.EventEntity

@Database(
    entities = [CategoryEntity::class, EventEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "App_database")
                    .fallbackToDestructiveMigration(true)
                    .build().also { Instance = it }
            }
        }

    }
}