package com.example.tarea_n2.data.repository.category

import com.example.tarea_n2.ui.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun obtenerTodasCategorias(): Flow<List<Category>>
    suspend fun sincronizarCategorias()

    suspend fun insertarCategoria(categoria: Category)

    suspend fun buscarPorNombre(nombre: String): Category?
}