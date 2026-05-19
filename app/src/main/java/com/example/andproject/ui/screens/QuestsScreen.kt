package com.example.andproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.andproject.ui.components.*
import com.example.andproject.ui.theme.*

@Composable
fun QuestsScreen(
    onAddTask: () -> Unit
) {
    // Временные данные – заменить на ViewModel
    var tasks by remember {
        mutableStateOf(
            listOf(
                Task(1, "Finish project report", "Due today", Priority.HIGH),
                Task(2, "Morning workout", "Due today", Priority.NORMAL),
                Task(3, "Read 20 pages", "Due tomorrow", Priority.LOW),
                Task(4, "Stand-up meeting", "Completed", Priority.NORMAL, isCompleted = true),
                Task(5, "Plan weekly goals", "Completed", Priority.NORMAL, isCompleted = true)
            )
        )
    }

    val xpCurrent = 620
    val xpMax = 1000
    val level = 12
    val xpToday = 180
    val streak = 5
    val doneCount = tasks.count { it.isCompleted }

    Scaffold(
        containerColor = BgDark,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTask,
                containerColor = Gold,
                contentColor = OnGold,
                shape = CircleShape,
                modifier = Modifier.size(52.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add quest")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Hero Header
            item {
                HeroSection(level = level, currentXp = xpCurrent, maxXp = xpMax)
            }
            // Статистика
            item {
                StatsRow(xpToday = xpToday, doneCount = doneCount, streak = streak)
            }
            // Заголовок списка
            item {
                SectionHeader(
                    title = "Active Quests",
                    subtitle = "${tasks.count { !it.isCompleted }} pending"
                )
            }
            // Список задач
            items(tasks, key = { it.id }) { task ->
                TaskItem(
                    task = task,
                    onToggleComplete = { toggled ->
                        tasks = tasks.map {
                            if (it.id == toggled.id) it.copy(isCompleted = !it.isCompleted) else it
                        }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun HeroSection(level: Int, currentXp: Int, maxXp: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BgCard)
            .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp))
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "Adventurer",
                        fontFamily = Nunito,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = TextMuted,
                        letterSpacing = 0.08.sp
                    )
                    Text(
                        text = "Arathorn",
                        fontFamily = Cinzel,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = GoldLight
                    )
                    Spacer(Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(PurpleAccent.copy(alpha = 0.15f))
                            .border(0.5.dp, PurpleAccent.copy(0.3f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            "🧙 Wizard of Focus",
                            fontSize = 10.sp,
                            fontFamily = Nunito,
                            fontWeight = FontWeight.Bold,
                            color = PurpleLight
                        )
                    }
                }
                // Avatar
                Box(contentAlignment = Alignment.BottomEnd) {
                    Box(
                        modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape)
                            .background(BgCard2)
                            .border(2.dp, Gold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("⚔️", fontSize = 28.sp)
                    }
                    Box(
                        modifier = Modifier
                            .offset(x = 4.dp, y = 4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Gold)
                            .padding(horizontal = 5.dp, vertical = 1.dp)
                    ) {
                        Text(
                            "Lv $level",
                            fontSize = 9.sp,
                            fontFamily = Cinzel,
                            fontWeight = FontWeight.Bold,
                            color = OnGold
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            XpBar(level = level, currentXp = currentXp, maxXp = maxXp)
        }
    }
}

@Composable
private fun StatsRow(xpToday: Int, doneCount: Int, streak: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatCard(
            icon = Icons.Default.Star,
            iconTint = Gold,
            value = "+$xpToday",
            label = "XP Today",
            modifier = Modifier.weight(1f)
        )
        StatCard(
            icon = Icons.Default.CheckCircle,
            iconTint = GreenAccent,
            value = "$doneCount",
            label = "Done",
            modifier = Modifier.weight(1f)
        )
        StatCard(
            icon = Icons.Default.Whatshot,
            iconTint = RedAccent,
            value = "$streak",
            label = "Streak",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontFamily = Cinzel,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = TextMuted,
            letterSpacing = 0.10.sp
        )
        Text(
            text = subtitle,
            fontFamily = Nunito,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            color = Gold
        )
    }
}