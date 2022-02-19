package softing.ubah4ukdev.mymapapplication.data.repository

import softing.ubah4ukdev.mymapapplication.data.repository.datasource.CacheDataSource
import softing.ubah4ukdev.mymapapplication.domain.models.MarkerDomain
import softing.ubah4ukdev.mymapapplication.domain.repository.CacheRepository

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.data.repository
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
class CacheRepositoryImpl(private val dataSource: CacheDataSource) : CacheRepository {
    override suspend fun addMarker(marker: MarkerDomain) =
        dataSource.addMarker(marker = marker)

    override suspend fun getMarkers(): List<MarkerDomain> =
        dataSource.getMarkers()

    override suspend fun removeMarker(markerId: Int) =
        dataSource.removeMarker(markerId = markerId)

    override suspend fun updateMarker(marker: MarkerDomain) =
        dataSource.updateMarker(marker = marker)
}