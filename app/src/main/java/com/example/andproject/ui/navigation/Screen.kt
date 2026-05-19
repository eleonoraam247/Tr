package com.example.andproject.navigation

sealed class Screen(val route: String, val title: String) {
    object Quests : Screen("quests", "Quests")
    object Stats : Screen("stats", "Chronicle")
    object Settings : Screen("settings", "Settings")
    object AddTask : Screen("add_task", "New Quest")
    object UsernameChange : Screen("username_change", "Change Username")
    object ClassPicker : Screen("class_picker", "Choose Class")
    object ResetProgress : Screen("reset_progress", "Reset Progress")
}