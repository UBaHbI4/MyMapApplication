package softing.ubah4ukdev.mymapapplication.data.repository.datasource

import softing.ubah4ukdev.mymapapplication.data.mappers.markerToDataLayer
import softing.ubah4ukdev.mymapapplication.data.storage.Storage
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
class CacheDataSourceImpl(private val storage: Storage) : CacheDataSource {
    override suspend fun addMarker(marker: MarkerDomain) =
        storage
            .storageDao()
            .addMarker(markerToDataLayer(marker))

    override suspend fun getMarkers(): List<MarkerDomain> =
        storage
            .storageDao()
            .getMarkers()
            .map { it.toDomain() }

    override suspend fun removeMarker(markerId: Int) =
        storage
            .storageDao()
            .removeMarker(markerId)

    override suspend fun updateMarker(marker: MarkerDomain) =
        storage
            .storageDao()
            .updateMarker(markerToDataLayer(marker))
}