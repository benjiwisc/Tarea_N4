package com.example.tarea_n2.data.repository.event

import com.example.tarea_n2.data.local.AppDatabase
import com.example.tarea_n2.data.local.entity.EventEntity
import com.example.tarea_n2.ui.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : EventRepository {

    override fun obtenerTodosEventos(): Flow<List<Event>> {
        return database.eventDao().obtenerTodos().map { listaEntities ->
            listaEntities.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun obtenerPorId(id: Int): Event? {
        return database.eventDao().obtenerPorId(id)?.toDomain()
    }

    override suspend fun insertarEvento(evento: Event) {
        database.eventDao().insertar(evento.toEntity())
    }

    override suspend fun borrarEvento(evento: Event) {
        database.eventDao().borrar(evento.toEntity())
    }
}

fun EventEntity.toDomain() = Event(
    id = this.id,
    nombre = this.nombre,
    fecha_hora = this.fecha_hora,
    lugar = this.lugar,
    representante = this.representante,
    category = this.category
)

fun Event.toEntity() = EventEntity(
    id = this.id,
    nombre = this.nombre,
    fecha_hora = this.fecha_hora,
    lugar = this.lugar,
    representante = this.representante,
    category = this.category
)
