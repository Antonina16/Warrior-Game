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
import androidx.compose.ui.graphics.Color
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


enum class StatType { Health, Attack, Defense, Vampirism, Heal }

data class StatItem(
    val type: StatType,
    val value: Int,
)

data class UnitMeta(
    val title: String,
    val iconRes: Int,
    val backgroundRes: Int,
    val description: String = ""
)

fun unitMetaOf(type: UnitType): UnitMeta = when (type) {
    UnitType.Warrior -> UnitMeta(
        title = "Warrior",
        iconRes = R.drawable.ic_launcher_foreground,
        backgroundRes = R.drawable.ic_launcher_foreground
    )

    UnitType.Knight -> UnitMeta(
        title = "Knight",
        iconRes = R.drawable.ic_launcher_foreground,
        backgroundRes = R.drawable.ic_launcher_foreground
    )

    UnitType.Rookie -> UnitMeta(
        title = "Rookie",
        iconRes = R.drawable.ic_launcher_foreground,
        backgroundRes = R.drawable.ic_launcher_foreground
    )

    UnitType.Lancer -> UnitMeta(
        title = "Lancer",
        iconRes = R.drawable.ic_launcher_foreground,
        backgroundRes = R.drawable.ic_launcher_foreground
    )

    UnitType.Vampire -> UnitMeta(
        title = "Vampire",
        iconRes = R.drawable.ic_launcher_foreground,
        backgroundRes = R.drawable.ic_launcher_foreground
    )

    UnitType.Defender -> UnitMeta(
        title = "Defender",
        iconRes = R.drawable.ic_launcher_foreground,
        backgroundRes = R.drawable.ic_launcher_foreground
    )

    UnitType.Healer -> UnitMeta(
        title = "Healer",
        iconRes = R.drawable.ic_launcher_foreground,
        backgroundRes = R.drawable.ic_launcher_foreground
    )
}


val StatIcon = mapOf(
    StatType.Health to R.drawable.ic_stat_health,
    StatType.Attack to R.drawable.ic_stat_attack,
    StatType.Defense to R.drawable.ic_stat_defense,
    StatType.Vampirism to R.drawable.ic_stat_vampirism,
    StatType.Heal to R.drawable.ic_stat_heal
)


fun statsFor(type: UnitType): List<StatItem> = when (type) {
    UnitType.Warrior -> listOf(
        StatItem(StatType.Health, Props.Warrior.HEALTH),
        StatItem(StatType.Attack, Props.Warrior.ATTACK)
    )

    UnitType.Knight -> listOf(
        StatItem(StatType.Health, Props.Knight.HEALTH),
        StatItem(StatType.Attack, Props.Knight.ATTACK)
    )

    UnitType.Rookie -> listOf(
        StatItem(StatType.Health, Props.Rookie.HEALTH),
        StatItem(StatType.Attack, Props.Rookie.ATTACK)
    )

    UnitType.Lancer -> listOf(
        StatItem(StatType.Health, Props.Lancer.HEALTH),
        StatItem(StatType.Attack, Props.Lancer.ATTACK)
    )

    UnitType.Vampire -> listOf(
        StatItem(StatType.Health, Props.Vampire.HEALTH),
        StatItem(StatType.Attack, Props.Vampire.ATTACK),
        StatItem(StatType.Vampirism, Props.Vampire.VAMPIRISM)
    )

    UnitType.Defender -> listOf(
        StatItem(StatType.Health, Props.Defender.HEALTH),
        StatItem(StatType.Attack, Props.Defender.ATTACK),
        StatItem(StatType.Defense, Props.Defender.DEFENSE)
    )

    UnitType.Healer -> listOf(
        StatItem(StatType.Health, Props.Healer.HEALTH),
        StatItem(StatType.Attack, Props.Healer.ATTACK),
        StatItem(StatType.Heal, Props.Healer.HEAL)
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
    val meta = unitMetaOf(type)
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
                painter = painterResource(meta.backgroundRes),
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
                    text = meta.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                UnitStatsRow(type)

            }
        }
    }
}


@Composable
private fun StatChip(
    item: StatItem,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(StatIcon[item.type]!!),
                contentDescription = item.type.name,
                tint = Color.Unspecified,
                modifier = Modifier.size(42.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = item.type.name, // Health / Attack / ...
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = item.value.toString(),
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
            )
        }
    }
}

@Composable
private fun UnitStatsRow(
    type: UnitType,
) {
    val all = statsFor(type)
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(all) { it ->
            StatChip(
                item = it,
            )
        }
    }
}

