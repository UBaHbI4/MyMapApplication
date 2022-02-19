package softing.ubah4ukdev.mymapapplication.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import softing.ubah4ukdev.mymapapplication.data.storage.entity.Marker

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.data.storage
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2022.02.19
 *
 *   v1.0
 */
@Database(
    exportSchema = true,
    entities = [Marker::class],
    version = 2
)
abstract class Storage : RoomDatabase() {
    abstract fun storageDao(): StorageDao
}