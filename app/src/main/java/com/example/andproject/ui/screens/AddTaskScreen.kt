package com.example.andproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.andproject.domain.model.Task
import com.example.andproject.ui.components.Priority
import com.example.andproject.ui.theme.*
import com.example.andproject.ui.viewmodel.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf(Priority.NORMAL) }
    var isReminderEnabled by remember { mutableStateOf(true) }
    var selectedDate by remember { mutableStateOf("Today") }

    Scaffold(
        containerColor = BgDark,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BgCard)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Gold, modifier = Modifier.size(20.dp))
                        }
                        Spacer(Modifier.width(12.dp))
                        Text(
                            text = "NEW QUEST",
                            fontFamily = Cinzel,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = GoldLight
                        )
                    }
                    Text(
                        text = "+${selectedPriority.xp} XP",
                        fontFamily = Cinzel,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Gold
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(BgDark)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // QUEST NAME
            SectionHeader(title = "QUEST NAME")
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text("What must be done, adventurer?", color = TextMuted, fontSize = 14.sp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = BgCard,
                    unfocusedContainerColor = BgCard,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    focusedBorderColor = Gold.copy(alpha = 0.5f),
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Gold
                ),
                shape = RoundedCornerShape(12.dp)
            )

            // PRIORITY
            SectionHeader(title = "PRIORITY")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Priority.entries.forEach { priority ->
                    PriorityChipV2(
                        priority = priority,
                        isSelected = selectedPriority == priority,
                        onClick = { selectedPriority = priority },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // DUE DATE
            SectionHeader(title = "DUE DATE")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DateButton(
                    title = "Today",
                    subtitle = "Finish today",
                    isSelected = selectedDate == "Today",
                    onClick = { selectedDate = "Today" },
                    modifier = Modifier.weight(1f)
                )
                DateButton(
                    title = "Tomorrow",
                    subtitle = "+1 day",
                    isSelected = selectedDate == "Tomorrow",
                    onClick = { selectedDate = "Tomorrow" },
                    modifier = Modifier.weight(1f)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(BgCard)
                    .clickable { /* Date Picker */ }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Pick a date", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    Text("Choose from calendar", color = TextMuted, fontSize = 11.sp)
                }
            }

            // REMINDER
            SectionHeader(title = "REMINDER")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(BgCard)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Notify me", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    Text("Get an alert before the deadline", color = TextMuted, fontSize = 11.sp)
                }
                Switch(
                    checked = isReminderEnabled,
                    onCheckedChange = { isReminderEnabled = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Gold,
                        uncheckedThumbColor = TextMuted,
                        uncheckedTrackColor = BgDark
                    )
                )
            }

            // XP REWARD BOX
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(BgCard)
                    .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("XP reward for this quest", color = TextMuted, fontSize = 14.sp)
                    Text(
                        text = selectedPriority.xp.toString(),
                        fontFamily = Cinzel,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Gold
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // ADD QUEST BUTTON
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        viewModel.addTask(
                            Task(
                                title = title,
                                description = "", // Image only shows title input
                                xpValue = selectedPriority.xp,
                                priority = selectedPriority.name
                            )
                        )
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Gold.copy(alpha = 0.8f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "ADD QUEST",
                    fontFamily = Cinzel,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = OnGold
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        color = TextMuted,
        letterSpacing = 0.1.sp
    )
}

@Composable
fun PriorityChipV2(
    priority: Priority,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) priority.color.copy(alpha = 0.08f) else BgCard)
            .border(
                width = if (isSelected) 1.5.dp else 0.dp,
                color = if (isSelected) priority.color else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = priority.name,
                color = if (isSelected) priority.color else TextMuted,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
            )
            Text(
                text = "+${priority.xp} XP",
                color = if (isSelected) priority.color.copy(alpha = 0.8f) else TextMuted.copy(alpha = 0.6f),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun DateButton(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(BgCard)
            .border(
                width = if (isSelected) 1.5.dp else 0.dp,
                color = if (isSelected) Gold else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(title, color = if (isSelected) Gold else TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text(subtitle, color = TextMuted, fontSize = 10.sp)
        }
    }
}
