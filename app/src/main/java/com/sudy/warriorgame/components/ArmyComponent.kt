package com.sudy.warriorgame.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudy.warriorgame.warriors.configs.DIM
import com.sudy.warriorgame.warriors.viewmodels.Callback
import com.sudy.warriorgame.warriors.viewmodels.UnitListEvent
import com.sudy.warriorgame.warriors.viewmodels.UnitListViewModel
import com.sudy.warriorgame.warriors.viewmodels.UnitListViewModelFactory
import org.koin.androidx.compose.koinViewModel


const val FILE_NAME = "cardlist.dat"



@Composable
fun ArmyGrid(
    vm: UnitListViewModel = koinViewModel()
) {
    val gridState = rememberLazyGridState()
    Box() {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            UnitDropDownComponent(onEvent = vm::onEvent)
            LazyVerticalGrid(
                columns = GridCells.Fixed(DIM),
                state = gridState,
                modifier = Modifier,
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    count = vm.itemList.size,
                    key = { it }
                ) { index ->
                    MiniUnitCard(
                        type = vm.itemList[index],
                        ix = index,
                        vm::onEvent
                    )
                }
            }
        }
        if (vm.isConfirmDialogOpen) {
            DeleteConfirmDialog(
                modifier = Modifier.align(Alignment.Center),
                callback = vm::onEvent,
                itemText = vm.itemToBeDeletedName
            )
        }
    }

}

@Composable
fun DeleteConfirmDialog(
    callback: Callback = {},
    itemText: String,
    title: String = "Are you sure?",
    text: String = "{type} will be permanently deleted.",
    confirmText: String = "Confirm",
    dismissText: String = "Cancel",
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { callback(UnitListEvent.CancelDelete) },
        icon = {
            Icon(
                Icons.Filled.Warning, contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        },
        title = { Text(title) },
        text = {
            Text(
                text.replace("{type}", itemText),
            )
        },
        confirmButton = {
            TextButton(
                onClick = { callback(UnitListEvent.ConfirmDelete) }
            ) {
                Text(confirmText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { callback(UnitListEvent.CancelDelete) }
            ) {
                Text(dismissText)
            }
        }
    )
}