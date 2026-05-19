package com.example.andproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Cinzel = FontFamily.Default
val Nunito = FontFamily.Default

val AppTypography = Typography(
    headlineLarge = TextStyle(fontFamily = Cinzel, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = GoldLight),
    headlineMedium = TextStyle(fontFamily = Cinzel, fontWeight = FontWeight.Bold, fontSize = 17.sp, color = GoldLight),
    bodyLarge = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Normal, fontSize = 14.sp, color = TextPrimary),
    bodyMedium = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = TextPrimary),
    bodySmall = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, color = TextMuted),
    titleLarge = TextStyle(fontFamily = Cinzel, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = GoldLight)
)