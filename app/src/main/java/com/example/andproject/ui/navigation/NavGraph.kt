package com.example.andproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.andproject.ui.screens.*
import com.example.andproject.ui.viewmodel.UserViewModel

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val userViewModel: UserViewModel = hiltViewModel()
    val userName by userViewModel.userName.collectAsState()
    val userClassId by userViewModel.userClass.collectAsState()
    val userProgress by userViewModel.userProgress.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Quests.route,
        modifier = modifier
    ) {
        composable(Screen.Quests.route) { 
            QuestsScreen(
                userName = userName,
                userClassId = userClassId,
                level = userProgress.level,
                currentXp = userProgress.currentXpInLevel,
                maxXp = userProgress.maxXpInLevel,
                onAddTask = { navController.navigate(Screen.AddTask.route) }
            ) 
        }
        composable(Screen.Stats.route) { StatsScreen() }
        composable(Screen.Settings.route) {
            SettingsScreen(
                userName = userName,
                userClassId = userClassId,
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
                currentUsername = userName,
                onUsernameChanged = { newName -> userViewModel.updateUserName(newName) }
            )
        }
        composable(Screen.ClassPicker.route) {
            ClassPickerScreen(
                navController = navController,
                currentClassId = userClassId,
                onClassChanged = { newClass -> userViewModel.updateUserClass(newClass.id) }
            )
        }
        composable(Screen.ResetProgress.route) {
            ResetProgressScreen(
                navController = navController,
                onResetConfirmed = { userViewModel.resetProgress() }
            )
        }
    }
}
