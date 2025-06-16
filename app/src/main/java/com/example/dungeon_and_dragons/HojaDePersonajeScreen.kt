package com.example.dungeon_and_dragons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

@Composable
fun HojaDePersonajeScreen(
    personajeInicial: Personaje?,
    onGuardar: (Personaje) -> Unit,
    onCancelar: () -> Unit
) {
    var pasoInicial by remember { mutableStateOf(personajeInicial == null) }
    var nombre by remember { mutableStateOf(personajeInicial?.nombre ?: "") }
    var raza by remember { mutableStateOf(personajeInicial?.raza ?: "") }
    var clase by remember { mutableStateOf(personajeInicial?.clase ?: "") }

    if (pasoInicial) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Text("Nuevo personaje", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = raza,
                onValueChange = { raza = it },
                label = { Text("Raza") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = clase,
                onValueChange = { clase = it },
                label = { Text("Clase") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (nombre.isNotBlank() && raza.isNotBlank() && clase.isNotBlank()) {
                            pasoInicial = false
                        }
                    }
                ) {
                    Text("Crear")
                }
                Button(onClick = onCancelar) {
                    Text("Cancelar")
                }
            }
        }
    } else {
        // Variables de estado para los campos de entrada
        var nivel by remember { mutableStateOf(personajeInicial?.nivel ?: "") }
        var trasfondo by remember { mutableStateOf(personajeInicial?.trasfondo ?: "") }
        var alineamiento by remember { mutableStateOf(personajeInicial?.alineamiento ?: "") }

        // Variables de estado para los atributos
        var fuerza by remember { mutableStateOf(personajeInicial?.fuerza ?: "") }
        var destreza by remember { mutableStateOf(personajeInicial?.destreza ?: "") }
        var constitucion by remember { mutableStateOf(personajeInicial?.constitucion ?: "") }
        var inteligencia by remember { mutableStateOf(personajeInicial?.inteligencia ?: "") }
        var sabiduria by remember { mutableStateOf(personajeInicial?.sabiduria ?: "") }
        var carisma by remember { mutableStateOf(personajeInicial?.carisma ?: "") }

        // Variables de estado para la lista de conjuros
        var conjuroNuevo by remember { mutableStateOf("") }
        var listaConjuros by remember { mutableStateOf(personajeInicial?.conjuros ?: emptyList()) }

        // Variables de estado para objetos
        var objetoNuevo by remember { mutableStateOf("") }
        var listaObjetos by remember { mutableStateOf(personajeInicial?.objetos ?: emptyList()) }

        // Variables de estado para armas
        var armaNueva by remember { mutableStateOf("") }
        var listaArmas by remember { mutableStateOf(personajeInicial?.armas ?: emptyList()) }

        // Variables de estado para habilidades especiales
        var habilidadNueva by remember { mutableStateOf("") }
        var listaHabilidades by remember { mutableStateOf(personajeInicial?.habilidades ?: emptyList()) }

        // Función para calcular el modificador
        fun calcularMod(atributo: String): String {
            val valor = atributo.toIntOrNull() ?: return ""
            val mod = (valor - 10) / 2
            return if (mod >= 0) "+$mod" else "$mod"
        }

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Text(
                text = "Hoja de Personaje D&D 5e",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = nivel,
                onValueChange = { nivel = it.filter { c -> c.isDigit() } },
                label = { Text("Nivel") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = raza,
                onValueChange = { raza = it },
                label = { Text("Raza") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = clase,
                onValueChange = { clase = it },
                label = { Text("Clase") },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            Divider()

            Spacer(modifier = Modifier.height(16.dp))
            Text("Atributos", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = fuerza,
                    onValueChange = { fuerza = it.filter { c -> c.isDigit() } },
                    label = { Text("Fuerza") },
                    modifier = Modifier.weight(1f),
                    trailingIcon = { Text(calcularMod(fuerza)) }
                )
                OutlinedTextField(
                    value = destreza,
                    onValueChange = { destreza = it.filter { c -> c.isDigit() } },
                    label = { Text("Destreza") },
                    modifier = Modifier.weight(1f),
                    trailingIcon = { Text(calcularMod(destreza)) }
                )
                OutlinedTextField(
                    value = constitucion,
                    onValueChange = { constitucion = it.filter { c -> c.isDigit() } },
                    label = { Text("Constitución") },
                    modifier = Modifier.weight(1f),
                    trailingIcon = { Text(calcularMod(constitucion)) }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = inteligencia,
                    onValueChange = { inteligencia = it.filter { c -> c.isDigit() } },
                    label = { Text("Inteligencia") },
                    modifier = Modifier.weight(1f),
                    trailingIcon = { Text(calcularMod(inteligencia)) }
                )
                OutlinedTextField(
                    value = sabiduria,
                    onValueChange = { sabiduria = it.filter { c -> c.isDigit() } },
                    label = { Text("Sabiduría") },
                    modifier = Modifier.weight(1f),
                    trailingIcon = { Text(calcularMod(sabiduria)) }
                )
                OutlinedTextField(
                    value = carisma,
                    onValueChange = { carisma = it.filter { c -> c.isDigit() } },
                    label = { Text("Carisma") },
                    modifier = Modifier.weight(1f),
                    trailingIcon = { Text(calcularMod(carisma)) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider()

            Spacer(modifier = Modifier.height(16.dp))
            Text("Conjuros", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = conjuroNuevo,
                    onValueChange = { conjuroNuevo = it },
                    label = { Text("Nuevo conjuro", color = Color(0xFFD6B36A)) },
                    textStyle = LocalTextStyle.current.copy(color = Color(0xFFD6B36A)),
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        if (conjuroNuevo.isNotBlank()) {
                            listaConjuros = listaConjuros + conjuroNuevo
                            conjuroNuevo = ""
                        }
                    }
                ) {
                    Text("Añadir")
                }
            }
            Column {
                listaConjuros.forEach { conjuro ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("• $conjuro", style = MaterialTheme.typography.bodyLarge, color = Color(0xFFD6B36A), modifier = Modifier.weight(1f))
                        IconButton(onClick = {
                            listaConjuros = listaConjuros - conjuro
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider()

            Spacer(modifier = Modifier.height(16.dp))
            Text("Objetos", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = objetoNuevo,
                    onValueChange = { objetoNuevo = it },
                    label = { Text("Nuevo objeto", color = Color(0xFFD6B36A)) },
                    textStyle = LocalTextStyle.current.copy(color = Color(0xFFD6B36A)),
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        if (objetoNuevo.isNotBlank()) {
                            listaObjetos = listaObjetos + objetoNuevo
                            objetoNuevo = ""
                        }
                    }
                ) {
                    Text("Añadir")
                }
            }
            Column {
                listaObjetos.forEach { objeto ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("• $objeto", style = MaterialTheme.typography.bodyLarge, color = Color(0xFFD6B36A), modifier = Modifier.weight(1f))
                        IconButton(onClick = {
                            listaObjetos = listaObjetos - objeto
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider()

            Spacer(modifier = Modifier.height(16.dp))
            Text("Armas", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = armaNueva,
                    onValueChange = { armaNueva = it },
                    label = { Text("Nueva arma", color = Color(0xFFD6B36A)) },
                    textStyle = LocalTextStyle.current.copy(color = Color(0xFFD6B36A)),
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        if (armaNueva.isNotBlank()) {
                            listaArmas = listaArmas + armaNueva
                            armaNueva = ""
                        }
                    }
                ) {
                    Text("Añadir")
                }
            }
            Column {
                listaArmas.forEach { arma ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("• $arma", style = MaterialTheme.typography.bodyLarge, color = Color(0xFFD6B36A), modifier = Modifier.weight(1f))
                        IconButton(onClick = {
                            listaArmas = listaArmas - arma
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider()

            Spacer(modifier = Modifier.height(16.dp))
            var habilidadNueva by remember { mutableStateOf("") }

            Text("Habilidades Especiales", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = habilidadNueva,
                    onValueChange = { habilidadNueva = it },
                    label = { Text("Nueva habilidad", color = Color(0xFFD6B36A)) },
                    textStyle = LocalTextStyle.current.copy(color = Color(0xFFD6B36A)),
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        if (habilidadNueva.isNotBlank()) {
                            listaHabilidades = listaHabilidades + habilidadNueva
                            habilidadNueva = ""
                        }
                    }
                ) {
                    Text("Añadir")
                }
            }
            Column {
                listaHabilidades.forEach { habilidad ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("• $habilidad", style = MaterialTheme.typography.bodyLarge, color = Color(0xFFD6B36A), modifier = Modifier.weight(1f))
                        IconButton(onClick = {
                            listaHabilidades = listaHabilidades - habilidad
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider()

            // --- Trasfondo y Alineamiento al final ---
            OutlinedTextField(
                value = trasfondo,
                onValueChange = { trasfondo = it },
                label = { Text("Trasfondo") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = alineamiento,
                onValueChange = { alineamiento = it },
                label = { Text("Alineamiento") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botones Guardar y Cancelar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    val personaje = Personaje(
                        nombre, nivel, raza, clase, trasfondo, alineamiento,
                        fuerza, destreza, constitucion, inteligencia, sabiduria, carisma,
                        listaConjuros, listaArmas, listaObjetos, listaHabilidades
                    )
                    onGuardar(personaje)
                }) {
                    Text("Guardar")
                }
                Button(onClick = onCancelar) {
                    Text("Cancelar")
                }
            }
        }
    }
}