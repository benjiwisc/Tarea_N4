package com.example.tarea_n2.ui.screens.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.tarea_n2.ui.components.BotonForm
import com.example.tarea_n2.ui.components.Icono
import com.example.tarea_n2.ui.components.InputForm
import com.example.tarea_n2.ui.navigation.Home

@Composable
fun FormScreenEvent(
    navController: NavHostController, 
    viewModel: FormViewModelEvent = hiltViewModel(),
    categoryViewModel: FormViewModelCategory = hiltViewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    val categoria by categoryViewModel.listCategory.collectAsStateWithLifecycle()
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.size(24.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icono(icono = Icons.Default.Add)
                Text(
                    text = "Nuevo Evento",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.size(24.dp))

            Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                OutlinedTextField(
                    value = viewModel.category,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Seleccione Categoría") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            "dropdown",
                            Modifier.clickable { expanded = !expanded }
                        )
                    },
                    isError = viewModel.categoryError != null,
                    supportingText = {
                        viewModel.categoryError?.let {
                            Text(it, color = MaterialTheme.colorScheme.error)
                        }
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    categoria.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.nombre) },
                            onClick = {
                                viewModel.category = category.nombre
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            InputForm(
                label = "Nombre del Evento",
                value = viewModel.nombre,
                onValueChange = { viewModel.nombre = it },
                error = viewModel.nombreError
            )

            Spacer(modifier = Modifier.size(16.dp))

            InputForm(
                label = "Fecha y Hora (Ej: 2024-12-01 18:00)",
                value = viewModel.fecha_hora,
                onValueChange = { viewModel.fecha_hora = it },
                error = viewModel.fechaHoraError
            )

            Spacer(modifier = Modifier.size(16.dp))

            InputForm(
                label = "Lugar",
                value = viewModel.lugar,
                onValueChange = { viewModel.lugar = it },
                error = viewModel.lugarError
            )

            Spacer(modifier = Modifier.size(16.dp))

            InputForm(
                label = "Representante",
                value = viewModel.representante,
                onValueChange = { viewModel.representante = it },
                error = viewModel.representanteError
            )

            Spacer(modifier = Modifier.size(32.dp))

            BotonForm(
                texto = "Registrar Evento",
                onClick = { 
                    viewModel.addEvent()
                }
            )

            Spacer(modifier = Modifier.size(12.dp))

            BotonForm(
                texto = "Volver",
                onClick = { navController.navigate(Home) }
            )
        }
    }
}
