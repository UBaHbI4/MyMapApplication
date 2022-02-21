package softing.ubah4ukdev.mymapapplication.data.storage

import androidx.room.*
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
@Dao
interface StorageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMarker(marker: Marker): Long

    @Query("SELECT * FROM tab_map_markers ORDER by title")
    fun getMarkers(): List<Marker>

    @Query("SELECT * FROM tab_map_markers WHERE markerId = :markerId")
    fun getMarkerById(markerId: Int): Marker

    @Query("DELETE FROM tab_map_markers WHERE markerId = :markerId")
    fun removeMarker(markerId: Int): Int

    @Update
    fun updateMarker(marker: Marker): Int
}