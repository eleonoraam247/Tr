package com.example.andproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.andproject.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage   = "com.google.android.gms",
    certificates      = R.array.com_google_android_gms_fonts_certs
)

val Cinzel = FontFamily(
    Font(googleFont = GoogleFont("Cinzel"), fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = GoogleFont("Cinzel"), fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = GoogleFont("Cinzel"), fontProvider = provider, weight = FontWeight.Bold),
)

val Nunito = FontFamily(
    Font(googleFont = GoogleFont("Nunito"), fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = GoogleFont("Nunito"), fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = GoogleFont("Nunito"), fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = GoogleFont("Nunito"), fontProvider = provider, weight = FontWeight.Bold),
)

// Переименована в LevelUpTypography чтобы не конфликтовать
// с классом androidx.compose.material3.Typography
val LevelUpTypography = Typography(
    headlineLarge  = TextStyle(fontFamily = Cinzel,  fontWeight = FontWeight.Bold,     fontSize = 22.sp, color = GoldLight, letterSpacing = 0.06.sp),
    headlineMedium = TextStyle(fontFamily = Cinzel,  fontWeight = FontWeight.Bold,     fontSize = 17.sp, color = GoldLight, letterSpacing = 0.05.sp),
    headlineSmall  = TextStyle(fontFamily = Cinzel,  fontWeight = FontWeight.Bold,     fontSize = 15.sp, color = GoldLight, letterSpacing = 0.05.sp),
    titleLarge     = TextStyle(fontFamily = Cinzel,  fontWeight = FontWeight.Bold,     fontSize = 18.sp, color = GoldLight),
    titleMedium    = TextStyle(fontFamily = Cinzel,  fontWeight = FontWeight.Bold,     fontSize = 13.sp, color = Gold),
    titleSmall     = TextStyle(fontFamily = Cinzel,  fontWeight = FontWeight.Bold,     fontSize = 11.sp, color = Gold),
    bodyLarge      = TextStyle(fontFamily = Nunito,  fontWeight = FontWeight.Normal,   fontSize = 14.sp, color = TextPrimary),
    bodyMedium     = TextStyle(fontFamily = Nunito,  fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = TextPrimary),
    bodySmall      = TextStyle(fontFamily = Nunito,  fontWeight = FontWeight.SemiBold, fontSize = 11.sp, color = TextMuted),
    labelSmall     = TextStyle(fontFamily = Nunito,  fontWeight = FontWeight.Bold,     fontSize = 10.sp, color = TextMuted, letterSpacing = 0.10.sp),
)