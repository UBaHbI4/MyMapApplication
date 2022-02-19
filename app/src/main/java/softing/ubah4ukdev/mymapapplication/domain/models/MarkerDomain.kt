package softing.ubah4ukdev.mymapapplication.domain.models

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
data class MarkerDomain(
    val markerId: Int,
    val latitude: Double,
    val longitude: Double,
    val visible: Boolean = true,
    val title: String,
    val description: String
)