package com.example.tarea_n2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tarea_n2.ui.screens.HomeScreen
import com.example.tarea_n2.ui.screens.DetailScreen
import com.example.tarea_n2.ui.screens.form.FormScreenCategory
import com.example.tarea_n2.ui.screens.form.FormScreenEvent
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object FormCategory

@Serializable
object FormEvent

@Serializable
data class Detail(val eventId: Int)

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home){
        composable<Home> {
            HomeScreen(navController)
        }

        composable<FormCategory> {
            FormScreenCategory(navController)
        }

        composable<FormEvent> {
            FormScreenEvent(navController)
        }

        composable<Detail> { backStackEntry ->
            val detail: Detail = backStackEntry.toRoute()
            DetailScreen(navController, detail.eventId)
        }
    }
}
