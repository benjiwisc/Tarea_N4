package com.example.tarea_n2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tarea_n2.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM CategoryEntity")
    fun obtenerTodos(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM CategoryEntity WHERE id = :id")
    suspend fun obtenerPorId(id: Int): CategoryEntity?

    @Query("SELECT * FROM CategoryEntity WHERE nombre = :nombre")
    suspend fun buscarPorNombre(nombre: String): CategoryEntity?

    @Insert
    suspend fun insertarTodos(vararg categorias: CategoryEntity)

    @Delete
    suspend fun borrar(categoria: CategoryEntity)
}