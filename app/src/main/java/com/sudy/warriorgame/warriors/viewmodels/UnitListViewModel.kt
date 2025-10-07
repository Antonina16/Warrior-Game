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

class UnitListViewModel : ViewModel() {
    private val _itemList =
        mutableStateListOf(UnitType.Warrior, UnitType.Lancer, UnitType.Vampire, UnitType.Knight)
    val itemList: List<UnitType> get() = _itemList

    var isConfirmDialogOpen by mutableStateOf(false)
        private set
    var itemToBeDeleted by mutableIntStateOf(-1)
        private set

    val itemToBeDeletedName: String get(){
        if(itemToBeDeleted !in itemList.indices) return ""
       return _itemList[itemToBeDeleted].name
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