package com.example.andproject.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Tasks : Screen("tasks")
    object AddTask : Screen("add_task")
    object Stats : Screen("stats")
    object Profile : Screen("profile")
}
