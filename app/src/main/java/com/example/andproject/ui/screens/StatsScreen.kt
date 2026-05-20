package com.example.andproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.andproject.ui.components.StatCard
import com.example.andproject.ui.theme.*

private data class Achievement(
    val emoji: String,
    val name: String,
    val description: String,
    val isEarned: Boolean,
    val progress: String = ""
)

@Composable
fun StatsScreen(
    totalXp: Int,
    tasksDone: Int,
    streak: Int,
    level: Int,
    currentXp: Int,
    maxXp: Int
) {
    // Достижения на основе реальных данных
    val achievements = listOf(
        Achievement("🔥", "On Fire!", "5-day streak completed",
            isEarned = streak >= 5, progress = "$streak/5"),
        Achievement("🏆", "First Quest", "Complete your first task",
            isEarned = tasksDone >= 1),
        Achievement("⚔️", "Centurion", "Complete 100 tasks total",
            isEarned = tasksDone >= 100, progress = "$tasksDone/100"),
        Achievement("👑", "Legendary", "Reach level 20",
            isEarned = level >= 20, progress = "Lv $level/20"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        // Заголовок
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BgCard)
                .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(0.dp))
                .padding(16.dp)
        ) {
            Text("⚡ Chronicle", fontFamily = Cinzel, fontWeight = FontWeight.Bold,
                fontSize = 18.sp, color = GoldLight)
        }

        // XP прогресс текущего уровня
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(BgCard)
                .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(12.dp))
                .padding(14.dp)
        ) {
            Text("Current Level Progress", fontFamily = Nunito, fontWeight = FontWeight.Bold,
                fontSize = 10.sp, color = TextMuted, letterSpacing = 0.08.sp)
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Level $level", fontFamily = Cinzel, fontWeight = FontWeight.Bold,
                    fontSize = 13.sp, color = GoldLight)
                Text("$currentXp / $maxXp XP", fontFamily = Cinzel,
                    fontWeight = FontWeight.Bold, fontSize = 11.sp, color = Gold)
            }
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth().height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White.copy(alpha = 0.06f))
                    .border(0.5.dp, Gold.copy(0.18f), RoundedCornerShape(4.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(currentXp.toFloat() / maxXp.coerceAtLeast(1))
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            androidx.compose.ui.graphics.Brush.horizontalGradient(
                                listOf(PurpleAccent, Gold)
                            )
                        )
                )
            }
        }

        // Stat cards — реальные данные
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatCard(Icons.Default.Star,         Gold,        "$totalXp",   "Total XP",   Modifier.weight(1f))
            StatCard(Icons.Default.CheckCircle,  GreenAccent, "$tasksDone", "Total Done", Modifier.weight(1f))
            StatCard(Icons.Default.Whatshot,     RedAccent,   "$streak",    "Streak",     Modifier.weight(1f))
        }

        // Achievements
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Achievements", fontFamily = Cinzel, fontWeight = FontWeight.Bold,
                fontSize = 13.sp, color = TextMuted, letterSpacing = 0.10.sp)
        }

        achievements.forEach { ach ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BgCard)
                    .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (ach.isEarned) Gold.copy(0.12f)
                            else Color.White.copy(0.04f)
                        )
                        .border(
                            0.5.dp,
                            if (ach.isEarned) Gold.copy(0.3f) else Gold.copy(0.10f),
                            RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) { Text(ach.emoji, fontSize = 18.sp) }

                Column(modifier = Modifier.weight(1f)) {
                    Text(ach.name, fontFamily = Nunito, fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = if (ach.isEarned) TextPrimary else TextMuted)
                    Text(ach.description, fontFamily = Nunito, fontSize = 10.sp, color = TextMuted)
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            if (ach.isEarned) GreenAccent.copy(0.15f)
                            else Color.White.copy(0.04f)
                        )
                        .border(
                            0.5.dp,
                            if (ach.isEarned) GreenAccent.copy(0.3f) else Gold.copy(0.10f),
                            RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 7.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (ach.isEarned) "Earned"
                        else if (ach.progress.isNotEmpty()) ach.progress
                        else "Locked",
                        fontSize   = 9.sp,
                        fontFamily = Nunito,
                        fontWeight = FontWeight.Bold,
                        color      = if (ach.isEarned) GreenAccent else TextMuted,
                        textAlign  = TextAlign.Center
                    )
                }
            }
        }
    }
}