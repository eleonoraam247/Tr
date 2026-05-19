package com.example.andproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.andproject.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameChangeScreen(
    navController: NavController,
    currentUsername: String,
    onUsernameChanged: (String) -> Unit
) {
    var newName by remember { mutableStateOf(TextFieldValue(currentUsername)) }
    var isValid by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    fun validate(name: String): Boolean {
        return when {
            name.length < 2 -> { errorMessage = "Too short — at least 2 characters"; false }
            name.length > 20 -> { errorMessage = "Too long — max 20 characters"; false }
            !name.matches(Regex("^[a-zA-Z0-9_]+$")) -> { errorMessage = "Only letters, numbers and underscores"; false }
            name.equals(currentUsername, ignoreCase = true) -> { errorMessage = "That's already your name!"; false }
            else -> { errorMessage = ""; true }
        }
    }

    Scaffold(
        containerColor = BgDark,
        topBar = {
            TopAppBar(
                title = { Text("Change Username", fontFamily = Cinzel, color = GoldLight) },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Аватар
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Gold, CircleShape)
                    .background(BgCard2),
                contentAlignment = Alignment.Center
            ) {
                Text("⚔️", fontSize = 34.sp)
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 4.dp, y = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Gold)
                        .padding(horizontal = 4.dp, vertical = 1.dp)
                ) {
                    Text("Lv 12", fontSize = 9.sp, fontFamily = Cinzel, color = OnGold)
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(currentUsername, fontFamily = Cinzel, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = GoldLight)
            Text("Current name", fontSize = 10.sp, color = TextMuted, letterSpacing = 0.08.sp)
            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = newName,
                onValueChange = {
                    newName = it
                    isValid = validate(it.text)
                },
                label = { Text("New username", color = TextMuted) },
                isError = !isValid,
                supportingText = {
                    if (!isValid) Text(errorMessage, color = RedAccent, fontSize = 11.sp)
                    else Text("2–20 characters, letters and numbers only", color = TextMuted, fontSize = 11.sp)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (isValid) Gold.copy(alpha = 0.5f) else RedAccent,
                    unfocusedBorderColor = Gold.copy(alpha = 0.2f),
                    focusedLabelColor = Gold,
                    cursorColor = Gold
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    if (validate(newName.text)) {
                        onUsernameChanged(newName.text)
                        navController.popBackStack()
                    }
                },
                enabled = isValid && newName.text.isNotBlank() && newName.text != currentUsername,
                colors = ButtonDefaults.buttonColors(containerColor = Gold),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text("Save Username", fontFamily = Cinzel, fontWeight = FontWeight.Bold, color = OnGold)
            }
        }
    }
}