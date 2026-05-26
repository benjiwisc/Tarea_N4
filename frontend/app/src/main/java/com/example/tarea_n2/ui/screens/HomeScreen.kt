package com.example.tarea_n2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.tarea_n2.ui.components.BotonForm
import com.example.tarea_n2.ui.components.Icono
import com.example.tarea_n2.ui.model.Category
import com.example.tarea_n2.ui.model.Event
import com.example.tarea_n2.ui.navigation.Detail
import com.example.tarea_n2.ui.navigation.FormCategory
import com.example.tarea_n2.ui.navigation.FormEvent
import com.example.tarea_n2.ui.screens.form.FormViewModelCategory
import com.example.tarea_n2.ui.screens.form.FormViewModelEvent

@Composable
fun HomeScreen(
    navController: NavController,
    viewModelEvent: FormViewModelEvent = hiltViewModel(),
    viewModelCategory: FormViewModelCategory = hiltViewModel()
) {
    val categorias by viewModelCategory.listCategory.collectAsStateWithLifecycle()
    val eventos by viewModelEvent.listEvent.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().height(80.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "EventMaster",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().height(80.dp)
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BotonForm(
                    texto = "Registro Categoria",
                    onClick = { navController.navigate(FormCategory) },
                    modifier = Modifier.weight(1f)
                )

                BotonForm(
                    texto = "Registro Evento",
                    onClick = { navController.navigate(FormEvent) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            GridCards(navController, categorias, eventos)
        }
    }
}

@Composable
fun GridCards(navController: NavController, categorias: List<Category>, eventos: List<Event>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(horizontal = 50.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        categorias.forEach { categoria ->
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icono(icono = Icons.Default.DateRange)
                    Text(
                        text = categoria.nombre,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }

            items(eventos.filter { it.category == categoria.nombre }) { evento ->
                Card(
                    colors = CardDefaults.cardColors(),
                    onClick = { navController.navigate(Detail(evento.id)) }
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = evento.nombre,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = evento.fecha_hora,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
