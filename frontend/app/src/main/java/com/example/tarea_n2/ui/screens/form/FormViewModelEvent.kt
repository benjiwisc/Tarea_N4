package com.example.tarea_n2.ui.screens.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarea_n2.data.repository.event.EventRepository
import com.example.tarea_n2.ui.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModelEvent @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    var nombre by mutableStateOf("")
    var fecha_hora by mutableStateOf("")
    var lugar by mutableStateOf("")
    var representante by mutableStateOf("")
    var category by mutableStateOf("")

    val listEvent: StateFlow<List<Event>> = eventRepository.obtenerTodosEventos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    var nombreError by mutableStateOf<String?>(null)
    var fechaHoraError by mutableStateOf<String?>(null)
    var lugarError by mutableStateOf<String?>(null)
    var representanteError by mutableStateOf<String?>(null)
    var categoryError by mutableStateOf<String?>(null)

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        // Podrías inicializar con datos de prueba si la lista está vacía, 
        // similar a como se hace en FormViewModelCategory
    }

    fun validar(): Boolean {
        var valido = true
        nombreError = if (nombre.isBlank()) "Campo obligatorio" else null
        fechaHoraError = if (fecha_hora.isBlank()) "Campo obligatorio" else null
        lugarError = if (lugar.isBlank()) "Campo obligatorio" else null
        representanteError = if (representante.isBlank()) "Campo obligatorio" else null
        categoryError = if (category.isBlank()) "Seleccione una categoría" else null

        if (nombreError != null || fechaHoraError != null || lugarError != null ||
            representanteError != null || categoryError != null
        ) valido = false

        return valido
    }

    fun addEvent() {
        if (validar()) {
            viewModelScope.launch {
                val nuevoEvento = Event(
                    nombre = nombre,
                    fecha_hora = fecha_hora,
                    lugar = lugar,
                    representante = representante,
                    category = category
                )
                eventRepository.insertarEvento(nuevoEvento)
                resetForm()
            }
        }
    }

    private fun resetForm() {
        nombre = ""
        fecha_hora = ""
        lugar = ""
        representante = ""
        category = ""
    }
}
