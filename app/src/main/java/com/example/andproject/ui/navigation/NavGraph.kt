package com.example.andproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.andproject.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Quests.route,
        modifier = modifier
    ) {
        composable(Screen.Quests.route) { QuestsScreen(onAddTask = { navController.navigate(Screen.AddTask.route) }) }
        composable(Screen.Stats.route) { StatsScreen() }
        composable(Screen.Settings.route) {
            SettingsScreen(
                onUsernameClick = { navController.navigate(Screen.UsernameChange.route) },
                onClassClick = { navController.navigate(Screen.ClassPicker.route) },
                onResetClick = { navController.navigate(Screen.ResetProgress.route) }
            )
        }
        composable(Screen.AddTask.route) { AddTaskScreen(navController, onTaskAdded = { _, _, _, _ -> }) }
        composable(Screen.UsernameChange.route) { UsernameChangeScreen(navController, currentUsername = "Arathorn", onUsernameChanged = {}) }
        composable(Screen.ClassPicker.route) { ClassPickerScreen(navController, currentClassId = "wizard", onClassChanged = {}) }
        composable(Screen.ResetProgress.route) { ResetProgressScreen(navController, onResetConfirmed = {}) }
    }
}