package com.sudy.warriorgame.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sudy.warriorgame.warriors.configs.Cartage
import com.sudy.warriorgame.warriors.configs.DIM
import com.sudy.warriorgame.warriors.configs.cartage


@Composable
fun ArmyGrid(
    state: Cartage = cartage,
    onClick: (Int) -> Unit = {}
) {
    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(DIM),
        state = gridState,
        modifier = Modifier, // .fillMaxSize().padding(16.dp)
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement   = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = state.size,
            key = { it }
        ) { index ->
            UnitCard(
                state[index],
                onClick = { onClick(index) }
            )
        }
    }
}