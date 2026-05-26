package com.example.tarea_n2.data.repository.category

import com.example.tarea_n2.data.local.AppDatabase
import com.example.tarea_n2.data.local.entity.CategoryEntity
import com.example.tarea_n2.ui.model.Category
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : CategoryRepository {

    override fun obtenerTodasCategorias(): Flow<List<Category>> {
        return database.categoryDao().obtenerTodos().map { listaEntities ->
            listaEntities.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun obtenerPorId(id: Int): Category? {
        return database.categoryDao().obtenerPorId(id)?.toDomain()
    }

    override suspend fun insertarCategoria(categoria: Category) {
        database.categoryDao().insertarTodos(categoria.toEntity())
    }

    override suspend fun borrarCategoria(categoria: Category) {
        database.categoryDao().borrar(categoria.toEntity())
    }

    override suspend fun buscarPorNombre(nombre: String): Category? {
        return database.categoryDao().buscarPorNombre(nombre)?.toDomain()
    }
}

fun CategoryEntity.toDomain() = Category(
    id = this.id,
    nombre = this.nombre,
    encargado = this.encargado
)

fun Category.toEntity() = CategoryEntity(
    id = this.id,
    nombre = this.nombre,
    encargado = this.encargado
)