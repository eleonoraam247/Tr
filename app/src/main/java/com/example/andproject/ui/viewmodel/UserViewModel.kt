package com.example.andproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andproject.data.datastore.PreferencesManager
import com.example.andproject.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserProgress(
    val level: Int,
    val currentXpInLevel: Int,
    val maxXpInLevel: Int,
    val totalXp: Int,
    val xpToday: Int,
    val streak: Int,
    val tasksDoneTotal: Int
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val taskRepository: TaskRepository
) : ViewModel() {

    val userName: StateFlow<String> = preferencesManager.userName.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), "Arathorn"
    )

    val userClass: StateFlow<String> = preferencesManager.userClass.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), "wizard"
    )

    // Один StateFlow с ВСЕМИ данными прогресса — нет рассинхрона между экранами
    val userProgress: StateFlow<UserProgress> = combine(
        preferencesManager.totalXp,
        preferencesManager.xpToday,
        preferencesManager.streak,
        preferencesManager.tasksDoneTotal
    ) { totalXp, xpToday, streak, tasksDone ->
        val level = (totalXp / 1000) + 1
        val currentXpInLevel = totalXp % 1000
        UserProgress(
            level            = level,
            currentXpInLevel = currentXpInLevel,
            maxXpInLevel     = 1000,
            totalXp          = totalXp,
            xpToday          = xpToday,
            streak           = streak,
            tasksDoneTotal   = tasksDone
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        UserProgress(1, 0, 1000, 0, 0, 0, 0)
    )

    fun updateUserName(name: String) {
        viewModelScope.launch { preferencesManager.updateUserName(name) }
    }

    fun updateUserClass(classId: String) {
        viewModelScope.launch { preferencesManager.updateUserClass(classId) }
    }

    fun resetProgress() {
        viewModelScope.launch {
            preferencesManager.resetProgress()
            taskRepository.deleteAllTasks()
        }
    }
}