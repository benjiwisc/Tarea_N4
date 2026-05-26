package com.example.tarea_n2.ui.screens.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarea_n2.data.repository.category.CategoryRepository
import com.example.tarea_n2.ui.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class FormViewModelCategory @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel(){
    var nombre by mutableStateOf("")
    var encargado by mutableStateOf("")
    var nombreError by mutableStateOf<String?>(null)
    var encargadoError by mutableStateOf<String?>(null)
    val listCategory: StateFlow<List<Category>> = categoryRepository.obtenerTodasCategorias()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            cargarDatos()
        }
    }

    fun cargarDatos() {
        viewModelScope.launch {
            val existente = categoryRepository.buscarPorNombre("Conciertos")
            if (existente == null) {
                categoryRepository.insertarCategoria(Category(nombre="Conciertos", encargado="Juan Perez"))
                categoryRepository.insertarCategoria(Category(nombre="Conferencias", encargado="Ana Garcia"))
                categoryRepository.insertarCategoria(Category(nombre="Talleres", encargado="Carlos Ruiz"))
            }
        }
    }

    fun validar(): Boolean {
        var valido = true

        nombreError = if (nombre.isBlank()) "Campo obligatorio" else null
        encargadoError = if (encargado.isBlank()) "Campo obligatorio" else null

        if (nombreError != null || encargadoError != null) valido = false

        return valido
    }

    fun addCategory(){
        if(validar()) {
            viewModelScope.launch {
                val categorynew = Category(
                    nombre = nombre,
                    encargado = encargado
                )
                categoryRepository.insertarCategoria(categorynew)
                resetForm()
            }
        }
    }

    fun resetForm() {
        nombre = ""
        encargado = ""
    }
}

