package com.example.andproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.andproject.ui.theme.*

@Composable
fun SettingsScreen(
    onUsernameClick: () -> Unit,
    onClassClick: () -> Unit,
    onResetClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .verticalScroll(rememberScrollState())
    ) {
        // Заголовок
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BgCard)
                .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(0.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "⚙️ Guild Settings",
                fontFamily = Cinzel,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = GoldLight
            )
        }

        // Безопасный баннер (необязательно, просто для демонстрации)
        SafeBanner()

        // Карточка профиля
        ProfileCard()

        // Профиль
        SettingsSection(title = "Profile")
        SettingsItem(
            icon = Icons.Default.Person,
            title = "Username",
            subtitle = "Arathorn",
            onClick = onUsernameClick
        )
        SettingsItem(
            icon = Icons.Default.Person,
            title = "Class",
            subtitle = "Wizard of Focus",
            onClick = onClassClick
        )

        // Уведомления
        SettingsSection(title = "Notifications")
        SwitchItem(
            icon = Icons.Default.Notifications,
            title = "Daily reminders",
            subtitle = "9:00 AM every day",
            defaultChecked = true
        )
        SwitchItem(
            icon = Icons.Default.Alarm,
            title = "Unfinished quest alerts",
            subtitle = "1 hour before midnight",
            defaultChecked = true
        )

        // Внешний вид
        SettingsSection(title = "Appearance")
        SwitchItem(
            icon = Icons.Default.BrightnessLow,
            title = "Dark mode",
            subtitle = "Always on",
            defaultChecked = true,
            enabled = false
        )

        // Данные
        SettingsSection(title = "Data")
        SettingsItem(
            icon = Icons.Default.Delete,
            title = "Reset progress",
            subtitle = "This cannot be undone",
            iconTint = RedAccent,
            textColor = RedAccent,
            onClick = onResetClick
        )
    }
}

@Composable
private fun SafeBanner() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(GreenAccent.copy(alpha = 0.08f))
            .border(0.5.dp, GreenAccent.copy(alpha = 0.25f), RoundedCornerShape(10.dp))
            .padding(10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Shield, contentDescription = null, tint = GreenAccent, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Progress safe — reset cancelled", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = GreenAccent)
        }
    }
}

@Composable
private fun ProfileCard() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 0.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(BgCard)
            .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(14.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .border(2.dp, Gold, CircleShape)
                .background(BgCard2),
            contentAlignment = Alignment.Center
        ) {
            Text("⚔️", fontSize = 26.sp)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 4.dp, y = 4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Gold)
                    .padding(horizontal = 4.dp, vertical = 1.dp)
            ) {
                Text("12", fontSize = 9.sp, fontFamily = Cinzel, color = OnGold)
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            Text("Arathorn", fontFamily = Cinzel, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = GoldLight)
            Text("🧙 Wizard of Focus", fontSize = 11.sp, color = TextMuted)
            Spacer(Modifier.height(6.dp))
            Box(modifier = Modifier.fillMaxWidth().height(5.dp).clip(RoundedCornerShape(3.dp)).background(Color.White.copy(alpha = 0.06f))) {
                Box(modifier = Modifier.fillMaxWidth(0.62f).fillMaxHeight().clip(RoundedCornerShape(3.dp)).background(Gold))
            }
            Text("620 / 1000 XP · Level 12", fontSize = 10.sp, color = TextMuted, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Composable
private fun SettingsSection(title: String) {
    Text(
        text = title.uppercase(),
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.1.sp,
        color = TextMuted,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 6.dp)
    )
}

@Composable
private fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    iconTint: Color = Gold,
    textColor: Color = TextPrimary,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = textColor)
            Text(subtitle, fontSize = 11.sp, color = TextMuted)
        }
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = TextMuted, modifier = Modifier.size(20.dp))
    }
}

@Composable
private fun SwitchItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    defaultChecked: Boolean,
    enabled: Boolean = true
) {
    var checked by remember { mutableStateOf(defaultChecked) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Gold, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text(subtitle, fontSize = 11.sp, color = TextMuted)
        }
        Switch(
            checked = checked,
            onCheckedChange = { if (enabled) checked = it },
            enabled = enabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Gold,
                checkedTrackColor = Gold.copy(alpha = 0.5f)
            )
        )
    }
}