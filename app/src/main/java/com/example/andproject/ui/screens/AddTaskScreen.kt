package com.example.andproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.andproject.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavController,
    onTaskAdded: (name: String, priority: String, dueDate: String, reminder: Boolean) -> Unit
) {
    var taskName by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("normal") } // normal, high, low
    var dueOption by remember { mutableStateOf("today") } // today, tomorrow, custom
    var customDate by remember { mutableStateOf("") }
    var reminderEnabled by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    val xpMap = mapOf("high" to 50, "normal" to 30, "low" to 20)
    val currentXp = xpMap[priority] ?: 30

    Scaffold(
        containerColor = BgDark,
        topBar = {
            TopAppBar(
                title = { Text("New Quest", fontFamily = Cinzel, color = GoldLight) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextMuted)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BgCard),
                actions = {
                    Text("+$currentXp XP", fontFamily = Cinzel, color = Gold, fontSize = 12.sp, modifier = Modifier.padding(end = 16.dp))
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BgDark)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Поле ввода названия
            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                label = { Text("Quest name", color = TextMuted) },
                placeholder = { Text("What must be done, adventurer?", color = TextMuted.copy(alpha = 0.5f)) },
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontFamily = Nunito, color = TextPrimary),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Gold.copy(alpha = 0.5f),
                    unfocusedBorderColor = Gold.copy(alpha = 0.2f),
                    focusedLabelColor = Gold,
                    unfocusedLabelColor = TextMuted
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Приоритет
            Text("Priority", fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.1.sp, color = TextMuted)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PriorityChip("high", "High", RedAccent, priority == "high") { priority = "high" }
                PriorityChip("normal", "Normal", PurpleAccent, priority == "normal") { priority = "normal" }
                PriorityChip("low", "Low", BlueAccent, priority == "low") { priority = "low" }
            }

            // Дедлайн
            Text("Due date", fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.1.sp, color = TextMuted)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                DueChip("today", "Today", dueOption == "today") { dueOption = "today" }
                DueChip("tomorrow", "Tomorrow", dueOption == "tomorrow") { dueOption = "tomorrow" }
            }
            if (dueOption == "custom") {
                OutlinedTextField(
                    value = customDate,
                    onValueChange = { customDate = it },
                    label = { Text("Pick a date", color = TextMuted) },
                    placeholder = { Text("YYYY-MM-DD", color = TextMuted.copy(alpha = 0.5f)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Gold.copy(alpha = 0.5f),
                        unfocusedBorderColor = Gold.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Button(
                    onClick = { dueOption = "custom" },
                    colors = ButtonDefaults.buttonColors(containerColor = BgCard),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = Gold, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Pick a custom date", color = TextPrimary, fontSize = 11.sp)
                    }
                }
            }

            // Напоминание
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(BgCard)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = Gold, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Notify me", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                    Text("Get an alert before the deadline", fontSize = 10.sp, color = TextMuted)
                }
                Switch(checked = reminderEnabled, onCheckedChange = { reminderEnabled = it }, colors = SwitchDefaults.colors(checkedThumbColor = Gold, checkedTrackColor = Gold.copy(alpha = 0.5f)))
            }

            // Карточка XP
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Gold.copy(alpha = 0.06f))
                    .border(0.5.dp, Gold.copy(alpha = 0.25f), RoundedCornerShape(10.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("XP reward for this quest", fontSize = 12.sp, color = TextMuted)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Bolt, contentDescription = null, tint = GoldLight, modifier = Modifier.size(16.dp))
                    Text("+$currentXp", fontFamily = Cinzel, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = GoldLight)
                }
            }

            Button(
                onClick = {
                    val dueLabel = when (dueOption) {
                        "today" -> "today"
                        "tomorrow" -> "tomorrow"
                        else -> customDate.ifEmpty { "a custom date" }
                    }
                    onTaskAdded(taskName, priority, dueLabel, reminderEnabled)
                    navController.popBackStack()
                },
                enabled = taskName.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = Gold),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Text("Add Quest — +$currentXp XP on complete", fontFamily = Cinzel, fontWeight = FontWeight.Bold, color = OnGold, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun PriorityChip(label: String, display: String, color: Color, selected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        color = if (selected) color.copy(alpha = 0.15f) else BgCard,
        border = if (selected) androidx.compose.foundation.BorderStroke(1.dp, color) else androidx.compose.foundation.BorderStroke(0.5.dp, Gold.copy(alpha = 0.2f))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
            Icon(
                imageVector = when (label) {
                    "high" -> Icons.Default.Whatshot
                    "normal" -> Icons.Default.Shield
                    else -> Icons.Default.Air
                },
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(20.dp)
            )
            Text(display, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = color)
            Text("+${if(label=="high")50 else if(label=="normal")30 else 20} XP", fontSize = 9.sp, color = color)
        }
    }
}

@Composable
fun DueChip(label: String, display: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        color = if (selected) Gold.copy(alpha = 0.1f) else BgCard,
        border = if (selected) androidx.compose.foundation.BorderStroke(1.dp, Gold) else androidx.compose.foundation.BorderStroke(0.5.dp, Gold.copy(alpha = 0.2f))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
            Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Gold, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
            Column {
                Text(display, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = if (selected) Gold else TextPrimary)
                Text(if (label == "today") "Finish today" else if (label == "tomorrow") "+1 day" else "", fontSize = 9.sp, color = TextMuted)
            }
        }
    }
}