package com.example.gymtrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Pantalla de Detalles: Muestra los ejercicios específicos del día seleccionado.
 */
@Composable
fun DetailsScreen(
    nombreDia: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Título dinámico según el día elegido
        Text(
            text = "Ejercicios de $nombreDia",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Elegimos qué ejercicios mostrar según el día
        val listaEjercicios = when (nombreDia) {
            "Lunes" -> listOf("Press de Banca: 4x12", "Aperturas: 3x15", "Fondos: 3x10")
            "Martes" -> listOf("Dominadas: 4x8", "Remo con Barra: 3x12", "Curl de Bíceps: 3x15")
            "Miércoles" -> listOf("Sentadillas: 4x10", "Prensa: 3x15", "Extensión de Piernas: 3x12")
            "Jueves" -> listOf("Press Militar: 4x10", "Elevaciones Laterales: 3x15", "Plancha: 3x1 min")
            "Viernes" -> listOf("Peso Muerto: 3x8", "Zancadas: 3x12", "Flexiones: 3x20")
            "Sábado" -> listOf("Correr: 30 min", "Abdominales: 3x20", "Burpees: 3x15")
            else -> listOf("Estiramientos: 15 min", "Caminar suave: 20 min", "Meditación: 10 min")
        }

        // Dibujamos cada ejercicio en la pantalla
        listaEjercicios.forEach { ejercicio ->
            val partes = ejercicio.split(": ")
            ItemEjercicio(
                nombre = partes[0],
                detalle = partes[1]
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón para regresar
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Regresar al Resumen")
        }
    }
}

/**
 * Diseño simple para cada renglón de ejercicio.
 */
@Composable
fun ItemEjercicio(nombre: String, detalle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = nombre, fontWeight = FontWeight.Bold)
            Text(text = detalle)
        }
    }
}
