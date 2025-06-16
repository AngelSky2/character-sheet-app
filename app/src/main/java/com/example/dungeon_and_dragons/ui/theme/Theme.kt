package com.example.dungeon_and_dragons.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dungeon_and_dragons.R

val MedievalFont = FontFamily(
    Font(R.font.medievalsharp_regular, FontWeight.Normal)
)

val MedievalTypography = Typography(
    displayLarge = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 32.sp),
    displayMedium = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 28.sp),
    displaySmall = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 24.sp),
    headlineLarge = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 22.sp),
    headlineMedium = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 20.sp),
    headlineSmall = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 18.sp),
    titleLarge = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    titleMedium = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    titleSmall = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    bodyLarge = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    labelLarge = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    labelMedium = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    labelSmall = TextStyle(fontFamily = MedievalFont, fontWeight = FontWeight.Normal, fontSize = 10.sp)
)

private val MedievalDarkColors = darkColorScheme(
    primary = Color(0xFFD6B36A),
    onPrimary = Color(0xFF2D1B08),
    background = Color(0xFF232026),
    onBackground = Color(0xFFD6B36A),
    surface = Color(0xFF2D1B08),
    onSurface = Color(0xFFD6B36A)
)

@Composable
fun MedievalDnDTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MedievalDarkColors,
        typography = MedievalTypography,
        content = content
    )
}


