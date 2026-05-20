package com.example.andproject.data.repository

import com.example.andproject.data.local.dao.TaskDao
import com.example.andproject.data.local.entity.toTask
import com.example.andproject.data.local.entity.toTaskEntity
import com.example.andproject.domain.model.Task
import com.example.andproject.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks().map { entities ->
            entities.map { it.toTask() }
        }
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)?.toTask()
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task.toTaskEntity())
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task.toTaskEntity())
    }

    override suspend fun clearAllTasks() {
        dao.clearAllTasks()
    }
}
