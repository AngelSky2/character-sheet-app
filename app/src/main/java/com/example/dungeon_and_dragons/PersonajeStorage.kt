package com.example.dungeon_and_dragons

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object PersonajeStorage {
    private const val PREFS_NAME = "dnd_prefs"
    private const val KEY_PERSONAJES = "personajes_json"

    // Guarda toda la lista de personajes
    fun guardarPersonajes(context: Context, personajes: List<Personaje>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val jsonArray = JSONArray()
        personajes.forEach { personaje ->
            jsonArray.put(personaje.toJsonObject())
        }
        prefs.edit().putString(KEY_PERSONAJES, jsonArray.toString()).apply()
    }

    // Carga toda la lista de personajes
    fun cargarPersonajes(context: Context): List<Personaje> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_PERSONAJES, null) ?: return emptyList()
        val jsonArray = JSONArray(json)
        return List(jsonArray.length()) { i ->
            jsonArray.getJSONObject(i).toPersonaje()
        }
    }

    // Borra un personaje por nombre (puedes cambiar por ID si lo agregas)
    fun borrarPersonaje(context: Context, nombre: String) {
        val personajes = cargarPersonajes(context).filter { it.nombre != nombre }
        guardarPersonajes(context, personajes)
    }
}

// Utilidades para convertir Personaje a JSONObject y viceversa
fun Personaje.toJsonObject(): JSONObject = JSONObject().apply {
    put("nombre", nombre)
    put("nivel", nivel)
    put("raza", raza)
    put("clase", clase)
    put("trasfondo", trasfondo)
    put("alineamiento", alineamiento)
    put("fuerza", fuerza)
    put("destreza", destreza)
    put("constitucion", constitucion)
    put("inteligencia", inteligencia)
    put("sabiduria", sabiduria)
    put("carisma", carisma)
    put("conjuros", JSONArray(conjuros))
    put("armas", JSONArray(armas))
    put("objetos", JSONArray(objetos))
    put("habilidades", JSONArray(habilidades))
}

fun JSONObject.toPersonaje(): Personaje = Personaje(
    nombre = getString("nombre"),
    nivel = getString("nivel"),
    raza = getString("raza"),
    clase = getString("clase"),
    trasfondo = getString("trasfondo"),
    alineamiento = getString("alineamiento"),
    fuerza = getString("fuerza"),
    destreza = getString("destreza"),
    constitucion = getString("constitucion"),
    inteligencia = getString("inteligencia"),
    sabiduria = getString("sabiduria"),
    carisma = getString("carisma"),
    conjuros = getJSONArray("conjuros").let { arr -> List(arr.length()) { arr.getString(it) } },
    armas = getJSONArray("armas").let { arr -> List(arr.length()) { arr.getString(it) } },
    objetos = getJSONArray("objetos").let { arr -> List(arr.length()) { arr.getString(it) } },
    habilidades = getJSONArray("habilidades").let { arr -> List(arr.length()) { arr.getString(it) } }
)