package softing.ubah4ukdev.mymapapplication.domain.models

import com.yandex.mapkit.geometry.Point

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.domain.models
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
data class NewMarkerResult(
    val result: Boolean,
    val position: Point
) : DataResult