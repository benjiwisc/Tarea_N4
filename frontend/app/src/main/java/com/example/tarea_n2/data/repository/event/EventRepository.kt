package com.example.tarea_n2.data.repository.event

import com.example.tarea_n2.ui.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun obtenerTodosEventos(): Flow<List<Event>>
    suspend fun obtenerPorId(id: Int): Event?
    suspend fun insertarEvento(evento: Event)
    suspend fun borrarEvento(evento: Event)
}
