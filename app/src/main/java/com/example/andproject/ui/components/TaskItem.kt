package com.example.andproject.ui.components

import androidx.compose.runtime.Composable
import com.example.andproject.domain.model.Task

@Composable
fun TaskItem(
    task: Task,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    // Basic stub for TaskItem
}
