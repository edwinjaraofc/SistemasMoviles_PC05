package com.example.gymtrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Clase simple para guardar los datos de cada día
// Usamos mutableStateOf para que Compose rastree los cambios en las propiedades
class EntrenamientoDia(
    val id: Int,
    val nombre: String,
    val enfoque: String,
    completadoInicial: Boolean = false,
    favoritoInicial: Boolean = false
) {
    var completado by mutableStateOf(completadoInicial)
    var favorito by mutableStateOf(favoritoInicial)
}

// Lista de los días de la semana con su rutina (Estado global simple para persistencia en memoria)
val rutinaSemanal = mutableStateListOf(
    EntrenamientoDia(1, "Lunes", "Pecho y Tríceps"),
    EntrenamientoDia(2, "Martes", "Espalda y Bíceps"),
    EntrenamientoDia(3, "Miércoles", "Piernas y Glúteos"),
    EntrenamientoDia(4, "Jueves", "Hombros y Abdominales"),
    EntrenamientoDia(5, "Viernes", "Full Body"),
    EntrenamientoDia(6, "Sábado", "Cardio y Core"),
    EntrenamientoDia(7, "Domingo", "Descanso Activo")
)

/**
 * Pantalla de Inicio: Muestra la lista de todos los días de la semana.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToDetails: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mi Semana Gym", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        // Lista que permite deslizar hacia abajo
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Creamos una tarjeta por cada día de la lista
            items(rutinaSemanal, key = { it.id }) { dia ->
                TarjetaEntrenamiento(
                    dia = dia,
                    onVerEjercicios = { onNavigateToDetails(dia.nombre) }
                )
            }
        }
    }
}

/**
 * Tarjeta individual para cada día con sus botones de estado.
 */
@Composable
fun TarjetaEntrenamiento(
    dia: EntrenamientoDia,
    onVerEjercicios: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    // Nombre del día
                    Text(
                        text = dia.nombre,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    // Enfoque del ejercicio
                    Text(
                        text = dia.enfoque,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                
                // Botón de corazón (Favorito)
                IconButton(onClick = { dia.favorito = !dia.favorito }) {
                    Icon(
                        imageVector = if (dia.favorito) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorito",
                        tint = if (dia.favorito) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón que cambia según el estado
            Button(
                onClick = { dia.completado = !dia.completado },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (dia.completado) 
                        MaterialTheme.colorScheme.tertiary 
                    else 
                        MaterialTheme.colorScheme.primary,
                    contentColor = if (dia.completado)
                        Color.White
                    else
                        MaterialTheme.colorScheme.onPrimary
                ),
                shape = if (dia.completado) 
                    MaterialTheme.shapes.large 
                else 
                    MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (dia.completado) "¡Rutina Completada!" else "Marcar como Completada"
                )
            }

            // Botón para navegar a detalles
            TextButton(
                onClick = onVerEjercicios,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Ver Ejercicios")
            }
        }
    }
}
