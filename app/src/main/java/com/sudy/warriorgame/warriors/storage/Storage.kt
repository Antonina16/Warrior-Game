package com.sudy.warriorgame.warriors.storage

import android.content.Context
import com.sudy.warriorgame.components.UnitType
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import javax.inject.Inject

//Dependency inversion principle: work with abstractions, not with concretions
interface StorageService {
    fun write(items: List<UnitType>)
    fun read(): List<UnitType>
}
 //Declarative injection with Hilt @Inject or you can use @Module and @Provides
class FileStorageService @Inject constructor(
    private val context: Context
) : StorageService {
    companion object {
        const val FILE_NAME: String = "cardlist.dat"
    }

    override fun write(items: List<UnitType>) {
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            val oos = ObjectOutputStream(it)
            oos.writeObject(
                ArrayList<UnitType>(items)
            )
        }
    }

    override fun read(): List<UnitType> {
        try {
            @Suppress("UNCHECKED_CAST")
            return ObjectInputStream(context.openFileInput(FILE_NAME))
                .readObject() as ArrayList<UnitType>
        } catch (e: Exception) {
            return emptyList()
        }
    }
}