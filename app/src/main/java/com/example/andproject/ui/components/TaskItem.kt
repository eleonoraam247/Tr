package com.example.andproject.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.andproject.domain.model.Task
import com.example.andproject.ui.theme.*

enum class Priority(val label: String, val xp: Int, val color: Color) {
    HIGH("High priority", 50, RedAccent),
    NORMAL("Normal", 30, PurpleAccent),
    LOW("Low priority", 20, BlueAccent);

    companion object {
        fun fromString(value: String): Priority {
            return entries.find { it.name == value } ?: NORMAL
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onToggleComplete: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val priority = Priority.fromString(task.priority)
    val priorityColor = priority.color
    val checkBg by animateColorAsState(
        targetValue = if (task.isCompleted) GreenAccent else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "checkBg"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(BgCard)
            .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(10.dp))
            .drawBehind {
                drawRect(
                    color = priorityColor,
                    size = Size(3.dp.toPx(), size.height)
                )
            }
            .padding(start = 14.dp, end = 12.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Checkbox
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(checkBg)
                .border(
                    width = 1.5.dp,
                    color = if (task.isCompleted) GreenAccent else Gold.copy(0.5f),
                    shape = CircleShape
                )
                .clickable { onToggleComplete(!task.isCompleted) },
            contentAlignment = Alignment.Center
        ) {
            if (task.isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = Color.White,
                    modifier = Modifier.size(13.dp)
                )
            }
        }

        // Task info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = task.title,
                fontFamily = Nunito,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = if (task.isCompleted) TextMuted else TextPrimary,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${task.description} · ${priority.label}",
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = TextMuted
            )
        }

        // XP reward
        Text(
            text = "+${task.xpValue} XP",
            fontFamily = Cinzel,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            color = if (task.isCompleted) GreenAccent else Gold
        )
    }
}
