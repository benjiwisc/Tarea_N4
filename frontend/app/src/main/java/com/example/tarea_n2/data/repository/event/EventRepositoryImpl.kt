package com.example.tarea_n2.data.repository.event

import android.util.Log
import com.example.tarea_n2.data.remote.dto.EventDto
import com.example.tarea_n2.data.remote.service.EventApiService
import com.example.tarea_n2.ui.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val apiService: EventApiService
) : EventRepository {

    override fun obtenerTodosEventos(): Flow<List<Event>> {
        return flow {
            try {
                emit(apiService.getEventos().map { it.toDomain() })
            } catch (e: Exception) {
                Log.e("EventRepository", "Error al obtener eventos desde la API " + e.message, e)
                emit(emptyList())
            }
        }
    }

    override suspend fun obtenerPorId(id: Int): Event? {
        return try {
            apiService.getEventoById(id).toDomain()
        } catch (e: Exception) {
            Log.e("EventRepository", "Error al obtener evento por id desde la API " + e.message, e)
            null
        }
    }

    override suspend fun insertarEvento(evento: Event) {
        apiService.createEvento(evento.toDto())
    }

    override suspend fun borrarEvento(evento: Event) {
        apiService.deleteEvento(evento.id)
    }
}

fun EventDto.toDomain() = Event(
    id = this.id,
    nombre = this.nombre,
    fecha_hora = this.fecha_hora,
    lugar = this.lugar,
    representante = this.representante,
    category = this.category
)

fun Event.toDto() = EventDto(
    id = this.id,
    nombre = this.nombre,
    fecha_hora = this.fecha_hora,
    lugar = this.lugar,
    representante = this.representante,
    category = this.category
)
