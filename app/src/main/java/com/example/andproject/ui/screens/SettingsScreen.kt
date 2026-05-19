package com.example.andproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.andproject.ui.theme.*

@Composable
fun SettingsScreen() {
    Column(Modifier.fillMaxSize().background(BgDark).verticalScroll(rememberScrollState())) {
        Box(Modifier.fillMaxWidth().background(BgCard).padding(16.dp)) {
            Text("⚙️ Guild Settings", fontFamily = Cinzel, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = GoldLight)
        }
        // Профиль
        SettingsSection("Profile")
        SettingsItem(icon = Icons.Default.Person, title = "Username", subtitle = "Arathorn", onClick = {})
        SettingsItem(icon = Icons.Default.Person, title = "Class", subtitle = "Wizard of Focus", onClick = {})
        // Уведомления
        SettingsSection("Notifications")
        SwitchItem(icon = Icons.Default.Notifications, title = "Daily reminders", subtitle = "9:00 AM every day", checked = true)
        SwitchItem(icon = Icons.Default.Alarm, title = "Unfinished quest alerts", subtitle = "1 hour before midnight", checked = true)
        // Данные
        SettingsSection("Data")
        SettingsItem(icon = Icons.Default.Delete, title = "Reset progress", subtitle = "This cannot be undone", iconTint = RedAccent, onClick = {})
    }
}

@Composable
private fun SettingsSection(title: String) {
    Text(title, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.1.sp, color = TextMuted, modifier = Modifier.padding(16.dp))
}

@Composable
private fun SettingsItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String, iconTint: androidx.compose.ui.graphics.Color = Gold, onClick: () -> Unit) {
    Row(Modifier.fillMaxWidth().clickable(onClick = onClick).padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = iconTint, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text(subtitle, fontSize = 11.sp, color = TextMuted)
        }
        Icon(Icons.Default.KeyboardArrowRight, null, tint = TextMuted, modifier = Modifier.size(20.dp))
    }
}

@Composable
private fun SwitchItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String, checked: Boolean) {
    var isChecked by remember { mutableStateOf(checked) }
    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = Gold, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text(subtitle, fontSize = 11.sp, color = TextMuted)
        }
        Switch(checked = isChecked, onCheckedChange = { isChecked = it }, colors = SwitchDefaults.colors(checkedThumbColor = Gold, checkedTrackColor = Gold.copy(alpha = 0.5f)))
    }
}