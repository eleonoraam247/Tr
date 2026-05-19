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
        composable(Screen.Quests.route) { 
            QuestsScreen(onAddTask = { navController.navigate(Screen.AddTask.route) }) 
        }
        composable(Screen.Stats.route) { StatsScreen() }
        composable(Screen.Settings.route) {
            SettingsScreen(
                onUsernameClick = { navController.navigate(Screen.UsernameChange.route) },
                onClassClick = { navController.navigate(Screen.ClassPicker.route) },
                onResetClick = { navController.navigate(Screen.ResetProgress.route) }
            )
        }
        composable(Screen.AddTask.route) { 
            AddTaskScreen(navController = navController) 
        }
        composable(Screen.UsernameChange.route) {
            UsernameChangeScreen(
                navController = navController,
                currentUsername = "Arathorn",
                onUsernameChanged = { /* TODO: Implement update */ }
            )
        }
        composable(Screen.ClassPicker.route) {
            ClassPickerScreen(
                navController = navController,
                currentClassId = "wizard",
                onClassChanged = { /* TODO: Implement update */ }
            )
        }
        composable(Screen.ResetProgress.route) {
            ResetProgressScreen(
                navController = navController,
                onResetConfirmed = { /* TODO: Implement reset */ }
            )
        }
    }
}
