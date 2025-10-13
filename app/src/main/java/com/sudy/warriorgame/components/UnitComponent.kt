package com.sudy.warriorgame.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sudy.warriorgame.ui.theme.backgroundLight
import com.sudy.warriorgame.warriors.configs.DIM
import com.sudy.warriorgame.warriors.viewmodels.Callback
import com.sudy.warriorgame.warriors.viewmodels.UnitListEvent
import kotlinx.coroutines.launch

const val MINI_UNIT_WIDTH = 200
const val MINI_UNIT_HEIGHT = 150
const val CHIPS_WIDTH = 175

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

@Composable
fun unitMetaOf(type: UnitType): UnitMeta = when (type) {
    UnitType.Warrior -> UnitMeta(
        title = stringResource(R.string.title_warrior),
        iconRes = R.drawable.ic_warrior_mini,
        backgroundRes = R.drawable.ic_warrior,
        description = stringResource(R.string.tagline_warrior)
    )

    UnitType.Knight -> UnitMeta(
        title = stringResource(R.string.title_knight),
        iconRes = R.drawable.ic_knight_mini,
        backgroundRes = R.drawable.ic_knight,
        description = stringResource(R.string.tagline_knight)
    )

    UnitType.Rookie -> UnitMeta(
        title = stringResource(R.string.title_rookie),
        iconRes = R.drawable.ic_rookie_mini,
        backgroundRes = R.drawable.ic_rookie,
        description = stringResource(R.string.tagline_rookie)
    )

    UnitType.Lancer -> UnitMeta(
        title = stringResource(R.string.title_lancer),
        iconRes = R.drawable.ic_lancer_mini,
        backgroundRes = R.drawable.ic_lancer,
        description = stringResource(R.string.tagline_lancer)
    )

    UnitType.Vampire -> UnitMeta(
        title = stringResource(R.string.title_vampire),
        iconRes = R.drawable.ic_vampire_mini,
        backgroundRes = R.drawable.ic_vampire,
        description = stringResource(R.string.tagline_vampire)
    )

    UnitType.Defender -> UnitMeta(
        title = stringResource(R.string.title_defender),
        iconRes = R.drawable.ic_defender_mini,
        backgroundRes = R.drawable.ic_defender,
        description = stringResource(R.string.tagline_defender)
    )

    UnitType.Healer -> UnitMeta(
        title = stringResource(R.string.title_healer),
        iconRes = R.drawable.ic_healer_mini,
        backgroundRes = R.drawable.ic_healer,
        description = stringResource(R.string.tagline_healer)
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
    onDelete: () -> Unit = {},
    modifier: Modifier = Modifier
        .fillMaxSize()
        .height(320.dp)
) {
    val meta = unitMetaOf(type)

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
            contentAlignment = Alignment.TopEnd

        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = meta.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Image(
                    painter = painterResource(meta.backgroundRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(400.dp)
                        .height(350.dp)
                        .alpha(.85f)
                )

                Text(
                    text = meta.description,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                UnitStatsGrid(type)
            }

            IconButton(
                modifier = Modifier
                    .padding(8.dp),
                onClick = onDelete,
            ) {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "delete",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniUnitCard(
    type: UnitType,
    ix: Int,
    onEvent: Callback = {},
    modifier: Modifier = Modifier
        .width(MINI_UNIT_WIDTH.dp)
        .height(MINI_UNIT_HEIGHT.dp)
) {
    val meta = unitMetaOf(type)
    var showInfo by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()


    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = { showInfo = true }
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
            contentAlignment = Alignment.TopEnd

        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top= 38.dp, start=6.dp, end=12.dp, bottom=6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(meta.iconRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)

                    )
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

                }

                UnitStatsRow(type)
            }
            IconButton(
                modifier = Modifier
                    .padding(bottom = 6.dp, end = 6.dp),
                onClick = {onEvent(UnitListEvent.OpenDeleteConfirmationDialog(ix))},
            ) {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "delete",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }

        }
    }
    if (showInfo) {
        ModalBottomSheet(
            onDismissRequest = { showInfo = false },
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() }
        ) {
            UnitCard(
                type = type,
                onDelete =
                    {
                        scope.launch {
                            sheetState.hide()
                            showInfo = false
                            onEvent(UnitListEvent.OpenDeleteConfirmationDialog(ix))
                        }
                    }
            )
        }
    }
}


@Composable
private fun StatChip(
    item: StatItem,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier.width(CHIPS_WIDTH.dp),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
    ) {
        Row(
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

@Composable
private fun UnitStatsGrid(
    type: UnitType,
) {
    val all = statsFor(type)
    val gridState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(DIM),
        state = gridState,
        modifier = Modifier,
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = all.size,
            key = { it }) { index ->
            StatChip(
                item = all[index],
            )
        }
    }
}


