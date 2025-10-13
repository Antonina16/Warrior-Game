package com.sudy.warriorgame

import android.app.Application
import com.sudy.warriorgame.warriors.storage.FileStorageService
import com.sudy.warriorgame.warriors.storage.InMemoryStorageService
import com.sudy.warriorgame.warriors.storage.StorageService
import com.sudy.warriorgame.warriors.viewmodels.UnitListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


class WarriorGameApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WarriorGameApplication)
            modules(appModule)
        }
    }
}

val appModule = module {
    single<StorageService>(qualifier = named("file")) {
        FileStorageService(androidContext())
    }
    single<StorageService> (qualifier = named("inMemory")){
        InMemoryStorageService()
    }
    viewModel {
        UnitListViewModel(get(named("file")))
    }
}