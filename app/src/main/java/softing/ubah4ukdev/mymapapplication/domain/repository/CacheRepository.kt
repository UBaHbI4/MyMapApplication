package softing.ubah4ukdev.mymapapplication.domain.repository

import softing.ubah4ukdev.mymapapplication.domain.models.MarkerDomain

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.domain.repository
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
interface CacheRepository {

    suspend fun addMarker(marker: MarkerDomain)

    suspend fun getMarkers(): List<MarkerDomain>

    suspend fun removeMarker(markerId: Int)

    suspend fun updateMarker(marker: MarkerDomain)
}