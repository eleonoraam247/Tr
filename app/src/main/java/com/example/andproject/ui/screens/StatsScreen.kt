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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.andproject.ui.components.StatCard
import com.example.andproject.ui.theme.*

@Composable
fun StatsScreen() {
    Column(
        Modifier.fillMaxSize().background(BgDark).verticalScroll(rememberScrollState())
    ) {
        // Заголовок
        Box(Modifier.fillMaxWidth().background(BgCard).border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(0.dp)).padding(16.dp)) {
            Text("⚡ Chronicle", fontFamily = Cinzel, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = GoldLight)
        }
        // График (упрощённо – можно позже доработать)
        Box(Modifier.padding(16.dp).clip(RoundedCornerShape(12.dp)).background(BgCard).border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(12.dp)).padding(14.dp)) {
            Text("Weekly XP chart", fontSize = 10.sp, color = TextMuted)
        }
        // Статистика
        Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            StatCard(Icons.Default.Star, Gold, "620", "Total XP", Modifier.weight(1f))
            StatCard(Icons.Default.CheckCircle, GreenAccent, "42", "Total Done", Modifier.weight(1f))
            StatCard(Icons.Default.CalendarMonth, BlueAccent, "18d", "Active Days", Modifier.weight(1f))
        }
        // Достижения (список)
        Text("Achievements", fontFamily = Cinzel, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextMuted, modifier = Modifier.padding(16.dp))
        // ... добавьте элементы достижений по аналогии с дизайном
    }
}