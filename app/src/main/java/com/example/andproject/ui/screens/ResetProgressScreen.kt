package com.example.andproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.andproject.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetProgressScreen(
    navController: NavController,
    onResetConfirmed: () -> Unit
) {
    var confirmationText by remember { mutableStateOf(TextFieldValue("")) }
    var isValid by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = BgDark,
        topBar = {
            TopAppBar(
                title = { Text("Reset Progress", fontFamily = Cinzel, color = RedAccent) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextMuted)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BgCard)
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = RedAccent.copy(alpha = 0.12f)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                border = androidx.compose.foundation.BorderStroke(0.5.dp, RedAccent.copy(alpha = 0.3f))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(20.dp)) {
                    Text("💀", fontSize = 38.sp)
                    Text("Your legend will be erased", fontFamily = Cinzel, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = RedAccent)
                    Spacer(Modifier.height(4.dp))
                    Text("All XP, levels, tasks, streaks and achievements will be permanently deleted. This action cannot be undone.", fontSize = 12.sp, color = TextMuted, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("You will lose", fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.1.sp, color = TextMuted, modifier = Modifier.fillMaxWidth())

            LoseItem(icon = "⭐", name = "All XP & Level", value = "Level 12 · 620 XP")
            LoseItem(icon = "🔥", name = "Current Streak", value = "5-day streak")
            LoseItem(icon = "📋", name = "All Tasks", value = "42 completed quests")
            LoseItem(icon = "🏆", name = "All Achievements", value = "2 earned badges")

            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = confirmationText,
                onValueChange = {
                    confirmationText = it
                    isValid = it.text == "RESET"
                },
                label = { Text("Type RESET to confirm", color = TextMuted) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (isValid) RedAccent else RedAccent.copy(alpha = 0.5f),
                    unfocusedBorderColor = RedAccent.copy(alpha = 0.2f),
                    focusedLabelColor = RedAccent
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    onResetConfirmed()
                    navController.popBackStack()
                },
                enabled = isValid,
                colors = ButtonDefaults.buttonColors(containerColor = RedAccent),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text("Erase Everything", fontFamily = Cinzel, fontWeight = FontWeight.Bold, color = Color.White)
            }
            TextButton(onClick = { navController.popBackStack() }) {
                Text("Keep My Progress", color = TextMuted)
            }
        }
    }
}

@Composable
fun LoseItem(icon: String, name: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(BgCard)
            .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(10.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(icon, fontSize = 18.sp, modifier = Modifier.width(28.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text(value, fontFamily = Cinzel, fontSize = 12.sp, color = RedAccent)
        }
        Icon(Icons.Default.Close, contentDescription = null, tint = RedAccent, modifier = Modifier.size(16.dp))
    }
}