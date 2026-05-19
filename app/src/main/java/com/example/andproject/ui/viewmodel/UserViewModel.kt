package com.example.andproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andproject.data.datastore.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
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
            // Here you might also want to clear tasks from Room if applicable
        }
    }
}
