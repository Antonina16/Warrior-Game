package com.sudy.warriorgame.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sudy.warriorgame.warriors.configs.Cartage
import com.sudy.warriorgame.warriors.configs.DIM
import com.sudy.warriorgame.warriors.configs.cartage
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
) {
    val context = LocalContext.current
    val itemList = remember {
        mutableStateListOf(
            *readData(context).toTypedArray()
        )
    }
    val gridState = rememberLazyGridState()

    @Composable
    fun updateData() {
        writeData(itemList, context)
    }
    updateData()

    fun deleteAt(ix: Int) {
        itemList.removeAt(ix)
    }

    fun add(type: UnitType) {
        itemList.add(type)
        println("devcpp itemList.size = ${itemList.size}")
    }

    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        UnitDropDownComponent(onCreate = ::add)
        LazyVerticalGrid(
            columns = GridCells.Fixed(DIM),
            state = gridState,
            modifier = Modifier,
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                count = itemList.size,
                key = { it }
            ) { index ->
                MiniUnitCard(
                    itemList[index],
                    onDelete = { deleteAt(index) }
                )
            }
        }
    }


}