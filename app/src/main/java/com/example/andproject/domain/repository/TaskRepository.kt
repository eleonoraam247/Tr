package com.example.andproject.domain.repository

import com.example.andproject.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>
    suspend fun getTaskById(id: Int): Task?
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun deleteAllTasks()   // ← добавлено для сброса прогресса
}