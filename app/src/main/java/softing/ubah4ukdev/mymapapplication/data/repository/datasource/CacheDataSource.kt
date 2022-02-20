package softing.ubah4ukdev.mymapapplication.data.repository.datasource

import softing.ubah4ukdev.mymapapplication.domain.AppState
import softing.ubah4ukdev.mymapapplication.domain.models.MarkerDomain

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.data.repository.datasource
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
interface CacheDataSource {
    suspend fun addMarker(marker: MarkerDomain): AppState
    suspend fun getMarkers(): AppState
    suspend fun getMarkerById(markerId: Int): AppState
    suspend fun removeMarker(markerId: Int): AppState
    suspend fun updateMarker(marker: MarkerDomain): AppState
}