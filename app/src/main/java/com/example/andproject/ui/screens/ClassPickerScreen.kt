package com.example.andproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.andproject.ui.theme.*

data class CharClass(val id: String, val emoji: String, val name: String, val desc: String, val bonus: String, val accent: Color)

val allClasses = listOf(
    CharClass("wizard", "🧙", "Wizard of Focus", "Master of deep work and concentration. Your spells are slow but devastating.", "+20% XP for long tasks", PurpleAccent),
    CharClass("warrior", "⚔️", "Warrior of Will", "Brute force and discipline. You tear through task lists without mercy.", "+2 XP per completed streak day", RedAccent),
    CharClass("ranger", "🏹", "Ranger of Routine", "Steady and consistent. Daily habits are your greatest weapon.", "Streak bonus never resets on weekends", GreenAccent),
    CharClass("rogue", "🗡️", "Rogue of Speed", "Quick, tactical, unpredictable. You thrive on rapid small wins.", "+15% XP when 3+ tasks done in 1 hr", BlueAccent),
    CharClass("paladin", "🛡️", "Paladin of Purpose", "Driven by mission and meaning. High-priority quests fuel your power.", "+25% XP for high-priority tasks", Gold)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassPickerScreen(
    navController: NavController,
    currentClassId: String,
    onClassChanged: (CharClass) -> Unit
) {
    var selectedClass by remember { mutableStateOf(allClasses.find { it.id == currentClassId } ?: allClasses[0]) }

    Scaffold(
        containerColor = BgDark,
        topBar = {
            TopAppBar(
                title = { Text("Choose Class", fontFamily = Cinzel, color = GoldLight) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextMuted)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BgCard)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(BgDark).padding(padding)) {
            LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(allClasses.size) { idx ->
                    val cls = allClasses[idx]
                    ClassCardItem(
                        charClass = cls,
                        isSelected = selectedClass.id == cls.id,
                        onClick = { selectedClass = cls }
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    onClassChanged(selectedClass)
                    navController.popBackStack()
                },
                enabled = selectedClass.id != currentClassId,
                colors = ButtonDefaults.buttonColors(containerColor = Gold),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().padding(16.dp).height(48.dp)
            ) {
                Text(if (selectedClass.id == currentClassId) "Current Class" else "Switch to ${selectedClass.name}", fontFamily = Cinzel, fontWeight = FontWeight.Bold, color = OnGold)
            }
        }
    }
}

@Composable
fun ClassCardItem(charClass: CharClass, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) charClass.accent.copy(alpha = 0.07f) else BgCard)
            .border(
                width = if (isSelected) 1.5.dp else 0.5.dp,
                color = if (isSelected) charClass.accent else Gold.copy(alpha = 0.18f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(charClass.accent.copy(alpha = 0.12f))
                .border(0.5.dp, charClass.accent.copy(alpha = 0.25f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(charClass.emoji, fontSize = 22.sp)
        }
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(charClass.name, fontFamily = Cinzel, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = GoldLight)
                if (isSelected) {
                    Spacer(Modifier.width(6.dp))
                    Icon(Icons.Default.Check, contentDescription = null, tint = charClass.accent, modifier = Modifier.size(14.dp))
                }
            }
            Text(charClass.desc, fontSize = 11.sp, color = TextMuted, maxLines = 2)
            Spacer(Modifier.height(4.dp))
            Text("⚡ ${charClass.bonus}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = charClass.accent)
        }
    }
}