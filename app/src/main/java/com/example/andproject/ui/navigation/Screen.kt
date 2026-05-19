package com.example.andproject.navigation

sealed class Screen(val route: String, val title: String) {
    object Quests : Screen("quests", "Quests")
    object Stats : Screen("stats", "Chronicle")
    object Settings : Screen("settings", "Settings")
}