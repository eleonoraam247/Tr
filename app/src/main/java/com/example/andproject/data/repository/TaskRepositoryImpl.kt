package com.example.andproject.data.repository

import com.example.andproject.data.local.dao.TaskDao
import com.example.andproject.data.local.entity.TaskEntity
import com.example.andproject.domain.model.Task
import com.example.andproject.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks().map { entities ->
            entities.map { it.toTask() }
        }
    }

    override suspend fun getTaskById(id: Int): Task? {  // Добавлен suspend
        return dao.getTaskById(id)?.toTask()
    }

    override suspend fun insertTask(task: Task) {  // Добавлен suspend
        dao.insertTask(TaskEntity.fromTask(task))
    }

    override suspend fun deleteTask(task: Task) {  // Добавлен suspend
        dao.deleteTask(TaskEntity.fromTask(task))
    }
}