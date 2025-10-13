package com.sudy.warriorgame.components

import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sudy.warriorgame.warriors.configs.DIM
import com.sudy.warriorgame.warriors.viewmodels.Callback
import com.sudy.warriorgame.warriors.viewmodels.UnitListEvent
import com.sudy.warriorgame.warriors.viewmodels.UnitListViewModel
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


const val FILE_NAME = "cardlist.dat"

fun writeData(items: List<UnitType>, context: Context) {
    context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
        val oos = ObjectOutputStream(it)
        oos.writeObject(
            ArrayList<UnitType>(items)
        )
    }
}

fun readData(context: Context): List<UnitType> {
    try {
        @Suppress("UNCHECKED_CAST")
        return ObjectInputStream(context.openFileInput(FILE_NAME))
            .readObject() as ArrayList<UnitType>
    } catch (e: Exception) {
        return emptyList()
    }
}

@Composable
fun ArmyGrid(
    vm: UnitListViewModel
) {
//    val context = LocalContext.current
//    val itemList = remember {
//        mutableStateListOf(
//            *readData(context).toTypedArray()
//        )
//    }
//
//    var isConfirmDialogOpen by rememberSaveable { mutableStateOf(false) }
//    var itemToBeDeleted by remember { mutableIntStateOf(-1) }

    val gridState = rememberLazyGridState()

//    LaunchedEffect(itemList.size) { writeData(itemList, context) }


//    @Composable
//    fun updateData() {
//        writeData(itemList, context)
//    }
//    updateData()

//    fun deleteAt(ix: Int) {
//        itemList.removeAt(ix)
//    }

//    fun deleteSelected(confirmed: Boolean) {
//        when {
//            !confirmed ->
//                itemToBeDeleted = -1
//
//            itemToBeDeleted !in itemList.indices ->
//                Log.wtf("List", "itemToBeDeleted out of bounds")
//
//            else -> run {  //return last expression in block code
//                itemList.removeAt(itemToBeDeleted)
//                itemToBeDeleted = -1
//            }
//
//        }
//        isConfirmDialogOpen = false
//    }
//
//    fun add(type: UnitType) {
//        itemList.add(type)
//        println("devcpp itemList.size = ${itemList.size}")
//    }
//
//    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol
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