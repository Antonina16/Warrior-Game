package com.sudy.warriorgame.warriors.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sudy.warriorgame.components.FILE_NAME
import com.sudy.warriorgame.components.UnitType
import com.sudy.warriorgame.warriors.configs.DIM
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


typealias Callback = (UnitListEvent) -> Unit

sealed interface UnitListEvent {
    data class OpenDeleteConfirmationDialog(val ix: Int) : UnitListEvent
    data object CancelDelete : UnitListEvent
    data object ConfirmDelete : UnitListEvent
    data class Add(val type: UnitType) : UnitListEvent
    data class OpenEditeDialog(val ix: Int) : UnitListEvent
    data object CancelEdite : UnitListEvent
    data object SubmitEdite : UnitListEvent

}

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

class UnitListViewModel(
    private val context: Context
) : ViewModel() {
    private val _itemList =
        mutableStateListOf(*readData(context = context).toTypedArray())
    val itemList: List<UnitType> get() = _itemList

    var isConfirmDialogOpen by mutableStateOf(false)
        private set
    var itemToBeDeleted by mutableIntStateOf(-1)
        private set

    val itemToBeDeletedName: String
        get() {
            if (itemToBeDeleted !in itemList.indices) return ""
            return _itemList[itemToBeDeleted].name
        }

    fun onEvent(event: UnitListEvent) {
        when (event) {
            is UnitListEvent.OpenDeleteConfirmationDialog -> delete(event.ix)
            is UnitListEvent.CancelDelete -> deleteSelected(confirmed = false)
            is UnitListEvent.ConfirmDelete -> deleteSelected(confirmed = true)
            is UnitListEvent.Add -> add(event.type)
            else -> {}
        }
    }

    fun deleteSelected(confirmed: Boolean) {
        when {
            !confirmed ->
                itemToBeDeleted = -1

            itemToBeDeleted !in itemList.indices ->
                Log.w("List", "itemToBeDeleted out of bounds")

            else -> run {  //return last expression in block code
                _itemList.removeAt(itemToBeDeleted)
                itemToBeDeleted = -1
                writeData(context = context, items = itemList)
            }

        }
        isConfirmDialogOpen = false
    }

    fun add(type: UnitType) {
        _itemList.add(type)
        writeData(context = context, items = itemList)
        println("devcpp itemList.size = ${itemList.size}")
    }

    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol

    fun delete(ix: Int) {
        itemToBeDeleted = ix
        isConfirmDialogOpen = true
    }


}