package com.example.dungeon_and_dragons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext

@Composable
fun PersonajeListScreen(
    onPersonajeSeleccionado: (Personaje) -> Unit,
    onNuevoPersonaje: () -> Unit
) {
    val context = LocalContext.current
    var personajes by remember { mutableStateOf(PersonajeStorage.cargarPersonajes(context)) }

    // Para refrescar la lista después de borrar
    fun refrescar() {
        personajes = PersonajeStorage.cargarPersonajes(context)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Tus personajes",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNuevoPersonaje,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear nuevo personaje")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(personajes) { personaje ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    onClick = { onPersonajeSeleccionado(personaje) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(personaje.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Nivel ${personaje.nivel} - ${personaje.clase}", style = MaterialTheme.typography.bodyLarge)
                        }
                        IconButton(onClick = {
                            PersonajeStorage.borrarPersonaje(context, personaje.nombre)
                            refrescar()
                        }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Borrar")
                        }
                    }
                }
            }
        }
    }
}