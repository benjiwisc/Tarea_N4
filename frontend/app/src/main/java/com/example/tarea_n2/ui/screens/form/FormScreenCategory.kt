package com.example.tarea_n2.ui.screens.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tarea_n2.ui.components.BotonForm
import com.example.tarea_n2.ui.components.Icono
import com.example.tarea_n2.ui.components.InputForm
import com.example.tarea_n2.ui.navigation.Home
import kotlinx.coroutines.launch

@Composable
fun FormScreenCategory(navController: NavHostController, formViewModel: FormViewModelCategory = hiltViewModel()) {
    val scope = rememberCoroutineScope()

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row() {
                Icono(icono = Icons.Default.DateRange)
                Text(
                    text = "Nueva Categoria",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            InputForm(
                label = "Ingrese el nombre de la categoria",
                value = formViewModel.nombre,
                onValueChange = { formViewModel.nombre = it },
                error = formViewModel.nombreError
            )

            Spacer(modifier = Modifier.size(22.dp))

            InputForm(
                label = "Ingrese el nombre del encargado",
                value = formViewModel.encargado,
                onValueChange = { formViewModel.encargado = it },
                error = formViewModel.encargadoError
            )

            Spacer(modifier = Modifier.size(32.dp))

            BotonForm(
                texto = "Añadir Categoria",
                onClick = {
                    scope.launch {formViewModel.addCategory()}
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

