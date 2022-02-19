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
    val markerId: Int = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val visible: Boolean = true,
    val title: String = "",
    val description: String = ""
)