package com.sudy.warriorgame.warriors.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sudy.warriorgame.warriors.storage.FileStorageService
import com.sudy.warriorgame.warriors.storage.StorageService

class UnitListViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {
 val storageService: StorageService = FileStorageService(context)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UnitListViewModel(storageService) as T
    }
}