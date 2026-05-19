package com.example.andproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.andproject.ui.screens.QuestsScreen
import com.example.andproject.ui.screens.StatsScreen
import com.example.andproject.ui.screens.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Quests.route,
        modifier = modifier
    ) {
        composable(Screen.Quests.route) {
            QuestsScreen(onAddTask = { /* открыть AddTaskScreen */ })
        }
        composable(Screen.Stats.route) {
            StatsScreen()
        }
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}