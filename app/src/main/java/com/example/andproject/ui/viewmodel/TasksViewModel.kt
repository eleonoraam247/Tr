package com.example.andproject.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.andproject.data.datastore.PreferencesManager
import com.example.andproject.data.worker.ReminderWorker
import com.example.andproject.domain.model.Task
import com.example.andproject.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    application: Application,
    private val repository: TaskRepository,
    private val preferencesManager: PreferencesManager
) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)

    val tasks: StateFlow<List<Task>> = repository.getTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onTaskCheckedChange(task: Task, isCompleted: Boolean) {
        viewModelScope.launch {
            repository.insertTask(task.copy(isCompleted = isCompleted))
            if (isCompleted) {
                preferencesManager.addXp(task.xpValue)
                // Cancel reminder if task is completed
                workManager.cancelAllWorkByTag(task.title)
            } else {
                preferencesManager.addXp(-task.xpValue)
            }
        }
    }

    fun addTask(task: Task, enableReminder: Boolean) {
        viewModelScope.launch {
            repository.insertTask(task)
            if (enableReminder) {
                scheduleReminder(task)
            }
        }
    }

    private fun scheduleReminder(task: Task) {
        val data = Data.Builder()
            .putString("task_title", task.title)
            .build()

        // For demo purposes, schedule in 10 seconds. 
        // In a real app, calculate delay based on task.dueDate
        val request = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setInputData(data)
            .addTag(task.title)
            .build()

        workManager.enqueue(request)
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            workManager.cancelAllWorkByTag(task.title)
        }
    }
}
