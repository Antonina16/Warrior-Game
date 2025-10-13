package com.sudy.warriorgame.warriors.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sudy.warriorgame.components.UnitType
import com.sudy.warriorgame.warriors.configs.DIM


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

class UnitListViewModel : ViewModel() {
    private val _itemList =
        mutableStateListOf(UnitType.Warrior, UnitType.Lancer, UnitType.Vampire, UnitType.Knight)
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
            }

        }
        isConfirmDialogOpen = false
    }

    fun add(type: UnitType) {
        _itemList.add(type)
        println("devcpp itemList.size = ${itemList.size}")
    }

    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol

    fun delete(ix: Int) {
        itemToBeDeleted = ix
        isConfirmDialogOpen = true
    }


}