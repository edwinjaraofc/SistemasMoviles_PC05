package com.example.gymtrack.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gymtrack.ui.screens.DetailsScreen
import com.example.gymtrack.ui.screens.HomeScreen
import com.example.gymtrack.ui.theme.GymTrackTheme

/**
 * Gestiona la navegación de toda la aplicación.
 */
@Composable
fun GymTrackApp() {
    val navController = rememberNavController()

    GymTrackTheme {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            // Pantalla Principal
            composable("home") {
                HomeScreen(
                    onNavigateToDetails = { nombreDia ->
                        // Navegamos pasando el nombre del día como parámetro
                        navController.navigate("details/$nombreDia")
                    }
                )
            }
            
            // Pantalla de Detalles (recibe el nombre del día)
            composable(
                route = "details/{nombreDia}",
                arguments = listOf(navArgument("nombreDia") { type = NavType.StringType })
            ) { backStackEntry ->
                val nombreDia = backStackEntry.arguments?.getString("nombreDia") ?: "Lunes"
                DetailsScreen(
                    nombreDia = nombreDia,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
