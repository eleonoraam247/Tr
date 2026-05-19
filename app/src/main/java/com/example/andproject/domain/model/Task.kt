package com.example.andproject.domain.model

data class Task(
    val id: Int? = null,
    val title: String,
    val description: String,
    val xpValue: Int,
    val isCompleted: Boolean = false,
    val dueDate: Long? = null,
    val priority: String = "NORMAL"
)
