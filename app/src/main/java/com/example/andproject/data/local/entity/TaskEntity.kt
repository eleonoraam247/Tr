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
) {
    fun toTask(): Task = Task(id, title, description, xpValue, isCompleted, dueDate, priority)
    
    companion object {
        fun fromTask(task: Task): TaskEntity = TaskEntity(
            id = task.id,
            title = task.title,
            description = task.description,
            xpValue = task.xpValue,
            isCompleted = task.isCompleted,
            dueDate = task.dueDate,
            priority = task.priority
        )
    }
}
