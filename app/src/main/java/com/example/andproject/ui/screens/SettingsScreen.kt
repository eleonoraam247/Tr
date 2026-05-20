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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.andproject.ui.theme.*

// CharClass и allClasses — из ClassPickerScreen.kt, тот же пакет, импорт не нужен

@Composable
fun SettingsScreen(
    userName: String,
    userClassId: String,
    level: Int,           // ← реальный уровень
    currentXp: Int,       // ← реальный XP
    maxXp: Int,           // ← реальный макс XP
    onUsernameClick: () -> Unit,
    onClassClick: () -> Unit,
    onResetClick: () -> Unit
) {
    val selectedClass = allClasses.find { it.id == userClassId } ?: allClasses[0]

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
            Text("⚙️ Guild Settings", fontFamily = Cinzel, fontWeight = FontWeight.Bold,
                fontSize = 18.sp, color = GoldLight)
        }

        // Карточка профиля — реальные данные
        Spacer(Modifier.height(16.dp))
        ProfileCard(
            userName  = userName,
            userClass = selectedClass,
            level     = level,
            currentXp = currentXp,
            maxXp     = maxXp
        )

        SettingsSection("Profile")
        SettingsGroup {
            SettingsItem(Icons.Default.Person,      "Username", userName,           onClick = onUsernameClick)
            SettingsDivider()
            SettingsItem(Icons.Default.AutoAwesome, "Class",    selectedClass.name, onClick = onClassClick)
        }

        SettingsSection("Notifications")
        SettingsGroup {
            SwitchItem(Icons.Default.Notifications, "Daily reminders",         "9:00 AM every day",      true)
            SettingsDivider()
            SwitchItem(Icons.Default.Alarm,         "Unfinished quest alerts",  "1 hour before midnight", true)
        }

        SettingsSection("Appearance")
        SettingsGroup {
            SwitchItem(Icons.Default.DarkMode, "Dark mode", "Always on", true, enabled = false)
        }

        SettingsSection("Data")
        SettingsGroup {
            SettingsItem(
                icon      = Icons.Default.DeleteForever,
                title     = "Reset progress",
                subtitle  = "This cannot be undone",
                iconTint  = RedAccent,
                textColor = RedAccent,
                onClick   = onResetClick
            )
        }

        Spacer(Modifier.height(24.dp))
        Text("LevelUp v1.0.0", fontFamily = Nunito, fontWeight = FontWeight.Bold,
            fontSize = 11.sp, color = TextMuted.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth().wrapContentWidth())
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun ProfileCard(
    userName: String,
    userClass: CharClass,
    level: Int,
    currentXp: Int,
    maxXp: Int
) {
    val xpFraction = (currentXp.toFloat() / maxXp.coerceAtLeast(1)).coerceIn(0f, 1f)

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(BgCard)
            .border(0.5.dp, Gold.copy(0.18f), RoundedCornerShape(14.dp))
            .padding(16.dp),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(54.dp).clip(CircleShape)
                    .background(BgCard2).border(2.dp, Gold, CircleShape),
                contentAlignment = Alignment.Center
            ) { Text(userClass.emoji, fontSize = 26.sp) }
            Box(
                modifier = Modifier
                    .offset(x = 4.dp, y = 4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Gold)
                    .padding(horizontal = 4.dp, vertical = 1.dp)
            ) {
                Text("$level", fontSize = 9.sp, fontFamily = Cinzel, color = OnGold)
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(userName, fontFamily = Cinzel, fontSize = 15.sp,
                fontWeight = FontWeight.Bold, color = GoldLight)
            Text("${userClass.emoji} ${userClass.name}", fontSize = 11.sp, color = TextMuted)
            Spacer(Modifier.height(6.dp))
            // XP bar — реальные данные
            Box(
                modifier = Modifier.fillMaxWidth().height(5.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.White.copy(alpha = 0.06f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(xpFraction).fillMaxHeight()
                        .clip(RoundedCornerShape(3.dp))
                        .background(Brush.horizontalGradient(listOf(PurpleAccent, Gold)))
                )
            }
            Text("$currentXp / $maxXp XP · Level $level", fontSize = 10.sp,
                color = TextMuted, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Composable
private fun SettingsSection(title: String) {
    Text(title.uppercase(), fontSize = 10.sp, fontWeight = FontWeight.Bold,
        letterSpacing = 0.10.sp, color = TextMuted,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 6.dp))
}

@Composable
private fun SettingsGroup(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(BgCard)
            .border(0.5.dp, Gold.copy(0.18f), RoundedCornerShape(12.dp)),
        content = content
    )
}

@Composable
private fun SettingsDivider() {
    HorizontalDivider(color = Gold.copy(0.10f), thickness = 0.5.dp,
        modifier = Modifier.padding(start = 46.dp))
}

@Composable
private fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    iconTint: Color  = Gold,
    textColor: Color = TextPrimary,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth().clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(icon, null, tint = iconTint, modifier = Modifier.size(20.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title,    fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = textColor)
            Text(subtitle, fontSize = 11.sp, color = TextMuted)
        }
        Icon(Icons.Default.ChevronRight, null, tint = TextMuted, modifier = Modifier.size(16.dp))
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
            .fillMaxWidth().padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(icon, null, tint = Gold, modifier = Modifier.size(20.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title,    fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text(subtitle, fontSize = 11.sp, color = TextMuted)
        }
        Switch(
            checked         = checked,
            onCheckedChange = { if (enabled) checked = it },
            enabled         = enabled,
            colors          = SwitchDefaults.colors(
                checkedThumbColor    = Color.White,
                checkedTrackColor    = Gold,
                uncheckedTrackColor  = Color.White.copy(0.10f),
                checkedBorderColor   = Color.Transparent,
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}