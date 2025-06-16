package com.example.dungeon_and_dragons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.dungeon_and_dragons.ui.theme.MedievalDnDTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedievalDnDTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    var personajeActual by remember { mutableStateOf<Personaje?>(null) }
                    val context = LocalContext.current

                    if (personajeActual == null) {
                        PersonajeListScreen(
                            onPersonajeSeleccionado = { personajeActual = it },
                            onNuevoPersonaje = { personajeActual = Personaje("", "", "", "", "", "") }
                        )
                    } else {
                        HojaDePersonajeScreen(
                            personajeInicial = personajeActual,
                            onGuardar = { personaje ->
                                val personajes = PersonajeStorage.cargarPersonajes(context)
                                    .filter { it.nombre != personaje.nombre } + personaje
                                PersonajeStorage.guardarPersonajes(context, personajes)
                                personajeActual = null
                            },
                            onCancelar = { personajeActual = null }
                        )
                    }
                }
            }
        }
    }
}