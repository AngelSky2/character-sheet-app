package com.example.dungeon_and_dragons

data class Personaje(
    val nombre: String,
    val nivel: String,
    val raza: String,
    val clase: String,
    val trasfondo: String,
    val alineamiento: String,
    val fuerza: String = "0",
    val destreza: String = "0",
    val constitucion: String = "0",
    val inteligencia: String = "0",
    val sabiduria: String = "0",
    val carisma: String = "0",
    val conjuros: List<String> = emptyList(),
    val armas: List<String> = emptyList(),
    val objetos: List<String> = emptyList(),
    val habilidades: List<String> = emptyList()
)