package com.example.andproject.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.andproject.domain.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    val xpValue: Int,
    val isCompleted: Boolean,
    val dueDate: Long?,
    val priority: String
)

fun TaskEntity.toTask(): Task = Task(
    id = id,
    title = title,
    description = description,
    xpValue = xpValue,
    isCompleted = isCompleted,
    dueDate = dueDate,
    priority = priority
)

fun Task.toTaskEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    description = description,
    xpValue = xpValue,
    isCompleted = isCompleted,
    dueDate = dueDate,
    priority = priority
)
