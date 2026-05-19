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
fun QuestsScreen(onAddTask: () -> Unit = {}) {
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

    Scaffold(
        containerColor = BgDark,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTask,
                containerColor = Gold,
                shape = CircleShape,
                modifier = Modifier.size(52.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item { HeroSection(level = 12, currentXp = 620, maxXp = 1000) }
            item { StatsRow(xpToday = 180, doneCount = tasks.count { it.isCompleted }, streak = 5) }
            item { SectionHeader("Active Quests", "${tasks.count { !it.isCompleted }} pending") }
            items(tasks, key = { it.id }) { task ->
                TaskItem(
                    task = task,
                    onToggleComplete = { toggled ->
                        tasks = tasks.map { if (it.id == toggled.id) it.copy(isCompleted = !it.isCompleted) else it }
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
            .padding(16.dp)
    ) {
        Column {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Adventurer", fontFamily = Nunito, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TextMuted)
                    Text("Arathorn", fontFamily = Cinzel, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = GoldLight)
                    Spacer(Modifier.height(6.dp))
                    Box(Modifier.clip(RoundedCornerShape(6.dp)).background(PurpleAccent.copy(alpha = 0.15f))
                        .border(0.5.dp, PurpleAccent.copy(0.3f), RoundedCornerShape(6.dp)).padding(horizontal = 8.dp, vertical = 3.dp)) {
                        Text("🧙 Wizard of Focus", fontSize = 10.sp, fontFamily = Nunito, fontWeight = FontWeight.Bold, color = PurpleLight)
                    }
                }
                Box(contentAlignment = Alignment.BottomEnd) {
                    Box(Modifier.size(58.dp).clip(CircleShape).background(BgCard2).border(2.dp, Gold, CircleShape), contentAlignment = Alignment.Center) {
                        Text("⚔️", fontSize = 28.sp)
                    }
                    Box(Modifier.offset(x = 4.dp, y = 4.dp).clip(RoundedCornerShape(8.dp)).background(Gold).padding(horizontal = 5.dp, vertical = 1.dp)) {
                        Text("Lv $level", fontSize = 9.sp, fontFamily = Cinzel, fontWeight = FontWeight.Bold, color = OnGold)
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
    Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        StatCard(Icons.Default.Star, Gold, "+$xpToday", "XP Today", Modifier.weight(1f))
        StatCard(Icons.Default.CheckCircle, GreenAccent, "$doneCount", "Done", Modifier.weight(1f))
        StatCard(Icons.Default.Whatshot, RedAccent, "$streak", "Streak", Modifier.weight(1f))
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(title, fontFamily = Cinzel, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextMuted)
        Text(subtitle, fontFamily = Nunito, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = Gold)
    }
}