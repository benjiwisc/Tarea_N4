package com.example.tarea_n2.data.repository.category


import android.util.Log
import com.example.tarea_n2.data.local.AppDatabase
import com.example.tarea_n2.data.local.entity.CategoryEntity
import com.example.tarea_n2.data.remote.service.CategoryApiService
import com.example.tarea_n2.data.remote.dto.CategoryDto
import com.example.tarea_n2.ui.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val apiService: CategoryApiService
) : CategoryRepository {

    override fun obtenerTodasCategorias(): Flow<List<Category>> {
        return database.categoryDao().obtenerTodos().map {
            it.map { it.toDomain() }
        }
    }

    override suspend fun insertarCategoria(categoria: Category) {
        val categoriaApi = apiService.createCategory(categoria.toDto())
        database.categoryDao().insertarTodos(categoriaApi.toEntity())
    }

    override suspend fun buscarPorNombre(nombre: String): Category? {
        return database.categoryDao().buscarPorNombre(nombre)?.toDomain()
    }

    override suspend fun sincronizarCategorias() {
        try {
            val categorias = apiService.getCategory()
            database.categoryDao().insertarTodos(*categorias.map { it.toEntity() }.toTypedArray())
        } catch (e: Exception) {
            Log.e("CategoryRepository", "Error al sincronizar categorias desde la API " + e.message, e)
        }
    }
}
fun CategoryEntity.toDomain() = Category(
    id = this.id,
    nombre = this.nombre,
    encargado = this.encargado
)

fun Category.toDto() = CategoryDto(
    id = this.id,
    nombre = this.nombre,
    encargado = this.encargado
)

fun CategoryDto.toEntity() = CategoryEntity(
    id = this.id,
    nombre = this.nombre,
    encargado = this.encargado
)

fun Category.toEntity() = CategoryEntity(
    id = this.id,
    nombre = this.nombre,
    encargado = this.encargado
)