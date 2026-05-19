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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.andproject.ui.components.StatCard
import com.example.andproject.ui.theme.*

@Composable
fun StatsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BgCard)
                .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(0.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "⚡ Статистика",
                fontFamily = Cinzel,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = GoldLight
            )
        }

        // Summary cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatCard(
                icon = Icons.Default.Star,
                iconTint = Gold,
                value = "1250",
                label = "Всего XP",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                icon = Icons.Default.CheckCircle,
                iconTint = GreenAccent,
                value = "42",
                label = "Выполнено",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                icon = Icons.Default.CalendarMonth,
                iconTint = BlueAccent,
                value = "18",
                label = "Дней",
                modifier = Modifier.weight(1f)
            )
        }
    }
}