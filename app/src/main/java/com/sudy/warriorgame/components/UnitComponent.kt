package com.sudy.warriorgame.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudy.warriorgame.R
import com.sudy.warriorgame.ui.theme.primaryContainerLight
import com.sudy.warriorgame.ui.theme.surfaceContainerLight
import com.sudy.warriorgame.warriors.configs.Props


enum class UnitType { Warrior, Knight, Rookie, Lancer, Vampire, Defender, Healer }


data class UnitStats(
    val title: String,
    val health: Int,
    val attack: Int,
    val extras: List<Pair<String, Int>> = emptyList(), // [("Vampirism",50), ...]
    val backgroundRes: Int
)


fun unitStatsOf(type: UnitType): UnitStats = when (type) {
    UnitType.Warrior -> UnitStats(
        title = "Warrior",
        health = Props.Warrior.HEALTH,
        attack = Props.Warrior.ATTACK,
        backgroundRes = R.drawable.ic_launcher_foreground
    )
    UnitType.Knight -> UnitStats(
        title = "Knight",
        health = Props.Knight.HEALTH,
        attack = Props.Knight.ATTACK,
        backgroundRes = R.drawable.ic_launcher_foreground
    )
    UnitType.Rookie -> UnitStats(
        title = "Rookie",
        health = Props.Rookie.HEALTH,
        attack = Props.Rookie.ATTACK,
        backgroundRes = R.drawable.ic_launcher_foreground
    )
    UnitType.Lancer -> UnitStats(
        title = "Lancer",
        health = Props.Lancer.HEALTH,
        attack = Props.Lancer.ATTACK,
        backgroundRes = R.drawable.ic_launcher_foreground
    )
    UnitType.Vampire -> UnitStats(
        title = "Vampire",
        health = Props.Vampire.HEALTH,
        attack = Props.Vampire.ATTACK,
        extras = listOf("Vampirism" to Props.Vampire.VAMPIRISM),
        backgroundRes = R.drawable.ic_launcher_foreground
    )
    UnitType.Defender -> UnitStats(
        title = "Defender",
        health = Props.Defender.HEALTH,
        attack = Props.Defender.ATTACK,
        extras = listOf("Defense" to Props.Defender.DEFENSE),
        backgroundRes = R.drawable.ic_launcher_foreground
    )
    UnitType.Healer -> UnitStats(
        title = "Healer",
        health = Props.Healer.HEALTH,
        attack = Props.Healer.ATTACK,
        extras = listOf("Heal" to Props.Healer.HEAL),
        backgroundRes = R.drawable.ic_launcher_foreground
    )
}

@Composable
fun UnitCard(
    type: UnitType,
    onClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
        .width(220.dp)
        .height(320.dp)
) {
    val stats = unitStatsOf(type)

    val allStats = remember(stats) {
        listOf("Health" to stats.health, "Attack" to stats.attack) + stats.extras
    }


    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(stats.backgroundRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0.35f)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stats.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        items = allStats,
                        key = { it.first }
                    ) { (name, value) ->
                        StatChip(label = name, value = value)
                    }
                }

//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.End,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    // Например, редкая рамка/иконка уровня и т.п.
//                }
            }
        }
    }
}

// Chip for displaying a stat
@Composable
private fun StatChip(
    label: String,
    value: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
