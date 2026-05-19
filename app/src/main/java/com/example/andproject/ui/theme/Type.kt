package com.example.andproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Временно используем системные шрифты
// Позже замените на загруженные шрифты
val Cinzel = FontFamily.Default
val Nunito = FontFamily.Default

val Typography = Typography(
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = GoldLight,
        letterSpacing = 0.06.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        color = GoldLight,
        letterSpacing = 0.05.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        color = GoldLight,
        letterSpacing = 0.05.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = TextPrimary
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        color = TextPrimary
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        color = TextMuted
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = GoldLight
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        color = Gold
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        color = Gold
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        color = TextMuted,
        letterSpacing = 0.10.sp
    )
)