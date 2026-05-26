package com.example.tarea_n2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tarea_n2.data.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM EventEntity")
    fun obtenerTodos(): Flow<List<EventEntity>>

    @Query("SELECT * FROM EventEntity WHERE id = :id")
    suspend fun obtenerPorId(id: Int): EventEntity?

    @Insert
    suspend fun insertar(evento: EventEntity)

    @Delete
    suspend fun borrar(evento: EventEntity)
}
