package com.example.andproject.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.andproject.navigation.Screen
import com.example.andproject.ui.theme.*

@Composable
fun BottomNavigationBar(
    items: List<Screen>,
    currentRoute: String,
    onItemClick: (Screen) -> Unit
) {
    NavigationBar(
        containerColor = BgCard,
        tonalElevation = 0.dp
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { onItemClick(screen) },
                icon = {
                    Icon(
                        imageVector = when (screen) {
                            Screen.Quests -> Icons.Default.Home
                            Screen.Stats -> Icons.Default.BarChart
                            Screen.Settings -> Icons.Default.Settings
                        },
                        contentDescription = screen.route
                    )
                },
                label = { Text(text = screen.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Gold,
                    selectedTextColor = Gold,
                    unselectedIconColor = TextMuted,
                    unselectedTextColor = TextMuted
                )
            )
        }
    }
}

// Добавьте свойство title в sealed class Screen (см. следующий шаг)