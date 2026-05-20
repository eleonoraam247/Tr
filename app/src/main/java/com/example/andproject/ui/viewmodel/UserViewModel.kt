package com.example.andproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andproject.data.datastore.PreferencesManager
import com.example.andproject.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserProgress(
    val level: Int,
    val currentXpInLevel: Int,
    val maxXpInLevel: Int,
    val totalXp: Int
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val taskRepository: TaskRepository
) : ViewModel() {

    val userName = preferencesManager.userName.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "Arathorn"
    )

    val userClass = preferencesManager.userClass.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "wizard"
    )

    val totalXp = preferencesManager.totalXp.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    // Прогресс: допустим, каждый уровень — 1000 XP
    val userProgress: StateFlow<UserProgress> = preferencesManager.totalXp.map { total ->
        val level = (total / 1000) + 1
        val currentXpInLevel = total % 1000
        UserProgress(
            level = level,
            currentXpInLevel = currentXpInLevel,
            maxXpInLevel = 1000,
            totalXp = total
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserProgress(1, 0, 1000, 0)
    )

    fun updateUserName(name: String) {
        viewModelScope.launch {
            preferencesManager.updateUserName(name)
        }
    }

    fun updateUserClass(classId: String) {
        viewModelScope.launch {
            preferencesManager.updateUserClass(classId)
        }
    }

    fun resetProgress() {
        viewModelScope.launch {
            preferencesManager.resetProgress()
            taskRepository.clearAllTasks()
        }
    }
}
