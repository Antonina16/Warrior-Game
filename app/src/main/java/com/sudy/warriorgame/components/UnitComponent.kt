package com.sudy.warriorgame.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import com.sudy.warriorgame.ui.theme.backgroundLight


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
    val description: String = "Some description here..." // TODO: add real descriptions
)

fun unitMetaOf(type: UnitType): UnitMeta = when (type) {
    UnitType.Warrior -> UnitMeta(
        title = "Warrior",
        iconRes = R.drawable.ic_warrior_mini,
        backgroundRes = R.drawable.ic_warrior
    )

    UnitType.Knight -> UnitMeta(
        title = "Knight",
        iconRes = R.drawable.ic_knight_mini,
        backgroundRes = R.drawable.ic_knight
    )

    UnitType.Rookie -> UnitMeta(
        title = "Rookie",
        iconRes = R.drawable.ic_rookie_mini,
        backgroundRes = R.drawable.ic_rookie
    )

    UnitType.Lancer -> UnitMeta(
        title = "Lancer",
        iconRes = R.drawable.ic_lancer_mini,
        backgroundRes = R.drawable.ic_lancer
    )

    UnitType.Vampire -> UnitMeta(
        title = "Vampire",
        iconRes = R.drawable.ic_vampire_mini,
        backgroundRes = R.drawable.ic_vampire
    )

    UnitType.Defender -> UnitMeta(
        title = "Defender",
        iconRes = R.drawable.ic_warrior_mini,
        backgroundRes = R.drawable.ic_defender
    )

    UnitType.Healer -> UnitMeta(
        title = "Healer",
        iconRes = R.drawable.ic_healer_mini,
        backgroundRes = R.drawable.ic_healer
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitCard(
    type: UnitType,
    onClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
        .width(220.dp)
        .height(320.dp)
) {
    val meta = unitMetaOf(type)

    var showInfo by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(6.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 1f),
                            MaterialTheme.colorScheme.tertiary.copy(alpha = .1f)
                        )
                    )
                ),

            ) {

            Image(
                painter = painterResource(meta.backgroundRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding( vertical = 58.dp)
                    .size(240.dp)
//                    .matchParentSize()
                    .alpha(.85f)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = meta.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    IconButton(onClick = { showInfo = true }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_info_circle),
                            contentDescription = "Info",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                UnitStatsRow(type)
            }
        }
    }

    if (showInfo) {
        ModalBottomSheet(
            onDismissRequest = { showInfo = false },
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(meta.iconRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(.85f)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = meta.title,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    text = meta.description,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(Modifier.height(16.dp))
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

