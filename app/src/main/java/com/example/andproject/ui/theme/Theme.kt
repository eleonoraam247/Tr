package com.example.andproject.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val LevelUpColorScheme = darkColorScheme(
    primary = Gold,
    onPrimary = OnGold,
    secondary = PurpleAccent,
    onSecondary = TextPrimary,
    background = BgDark,
    onBackground = TextPrimary,
    surface = BgCard,
    onSurface = TextPrimary,
    surfaceVariant = BgCard2,
    onSurfaceVariant = TextMuted,
    error = RedAccent,
    onError = TextPrimary,
)

@Composable
fun LevelUpTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LevelUpColorScheme,
        typography = Typography,
        content = content
    )
}