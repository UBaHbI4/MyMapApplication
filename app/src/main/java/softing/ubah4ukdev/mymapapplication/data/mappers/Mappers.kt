package softing.ubah4ukdev.mymapapplication.data.mappers

import softing.ubah4ukdev.mymapapplication.data.storage.entity.Marker
import softing.ubah4ukdev.mymapapplication.domain.models.MarkerDomain

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.data.mappers
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

fun markerToDataLayer(target: MarkerDomain): Marker =
    Marker(
        markerId = target.markerId,
        latitude = target.latitude,
        longitude = target.longitude,
        visible = target.visible,
        title = target.title,
        description = target.description
    )