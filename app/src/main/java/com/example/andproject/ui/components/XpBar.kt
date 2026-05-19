package com.example.andproject.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.andproject.ui.theme.*

@Composable
fun XpBar(
    level: Int,
    currentXp: Int,
    maxXp: Int,
    modifier: Modifier = Modifier
) {
    val progress by animateFloatAsState(
        targetValue = currentXp.toFloat() / maxXp.coerceAtLeast(1),
        animationSpec = tween(durationMillis = 600),
        label = "xpProgress"
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Level $level",
                fontSize = 11.sp,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                color = TextMuted
            )
            Text(
                text = "$currentXp / $maxXp XP",
                fontSize = 11.sp,
                fontFamily = Cinzel,
                fontWeight = FontWeight.Bold,
                color = Gold
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White.copy(alpha = 0.06f))
                .border(0.5.dp, Gold.copy(alpha = 0.18f), RoundedCornerShape(4.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Brush.horizontalGradient(listOf(PurpleAccent, Gold)))
            )
        }
    }
}