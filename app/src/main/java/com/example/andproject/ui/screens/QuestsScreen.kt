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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.andproject.ui.components.*
import com.example.andproject.ui.theme.*
import com.example.andproject.ui.viewmodel.TasksViewModel

@Composable
fun QuestsScreen(
    userName: String,
    userClassId: String,
    level: Int,
    currentXp: Int,
    maxXp: Int,
    xpToday: Int,      // ← реальные данные из DataStore
    streak: Int,       // ← реальные данные из DataStore
    onAddTask: () -> Unit,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedClass = allClasses.find { it.id == userClassId } ?: allClasses[0]

    val activeTasksCount = tasks.count { !it.isCompleted }
    val doneCount        = tasks.count { it.isCompleted }

    Scaffold(
        containerColor = BgDark,
        floatingActionButton = {
            FloatingActionButton(
                onClick        = onAddTask,
                containerColor = Gold,
                contentColor   = OnGold,
                shape          = CircleShape,
                modifier       = Modifier.size(52.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add quest")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier       = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                HeroSection(
                    userName  = userName,
                    userClass = selectedClass,
                    level     = level,
                    currentXp = currentXp,
                    maxXp     = maxXp
                )
            }
            item {
                StatsRow(xpToday = xpToday, doneCount = doneCount, streak = streak)
            }
            item {
                SectionHeader(
                    title    = "Active Quests",
                    subtitle = "$activeTasksCount pending"
                )
            }

            if (tasks.isEmpty()) {
                item {
                    Box(
                        modifier         = Modifier.fillMaxWidth().padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("📜", fontSize = 40.sp)
                            Text("No quests yet", fontFamily = Cinzel,
                                fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextMuted)
                            Text("Tap + to add your first quest", fontFamily = Nunito,
                                fontSize = 12.sp, color = TextMuted)
                        }
                    }
                }
            } else {
                items(tasks, key = { it.id ?: it.hashCode() }) { task ->
                    TaskItem(
                        task             = task,
                        onToggleComplete = { isCompleted ->
                            viewModel.onTaskCheckedChange(task, isCompleted)
                        },
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroSection(
    userName: String,
    userClass: CharClass,
    level: Int,
    currentXp: Int,
    maxXp: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BgCard)
            .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(0.dp))
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 16.dp)
    ) {
        Column {
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.Top
            ) {
                Column {
                    Text("Adventurer", fontFamily = Nunito, fontWeight = FontWeight.Bold,
                        fontSize = 11.sp, color = TextMuted, letterSpacing = 0.08.sp)
                    Text(userName, fontFamily = Cinzel, fontWeight = FontWeight.Bold,
                        fontSize = 20.sp, color = GoldLight)
                    Spacer(Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(userClass.accent.copy(alpha = 0.15f))
                            .border(0.5.dp, userClass.accent.copy(0.3f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text("${userClass.emoji} ${userClass.name}", fontSize = 10.sp,
                            fontFamily = Nunito, fontWeight = FontWeight.Bold,
                            color = userClass.accent)
                    }
                }
                Box(contentAlignment = Alignment.BottomEnd) {
                    Box(
                        modifier = Modifier
                            .size(58.dp).clip(CircleShape)
                            .background(BgCard2).border(2.dp, Gold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) { Text(userClass.emoji, fontSize = 28.sp) }
                    Box(
                        modifier = Modifier
                            .offset(x = 4.dp, y = 4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Gold)
                            .padding(horizontal = 5.dp, vertical = 1.dp)
                    ) {
                        Text("Lv $level", fontSize = 9.sp, fontFamily = Cinzel,
                            fontWeight = FontWeight.Bold, color = OnGold)
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
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatCard(Icons.Default.Star,        Gold,        "+$xpToday", "XP Today", Modifier.weight(1f))
        StatCard(Icons.Default.CheckCircle, GreenAccent, "$doneCount", "Done",    Modifier.weight(1f))
        StatCard(Icons.Default.Whatshot,    RedAccent,   "$streak",   "Streak",   Modifier.weight(1f))
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text(title, fontFamily = Cinzel, fontWeight = FontWeight.Bold,
            fontSize = 13.sp, color = TextMuted, letterSpacing = 0.10.sp)
        Text(subtitle, fontFamily = Nunito, fontWeight = FontWeight.Bold,
            fontSize = 11.sp, color = Gold)
    }
}