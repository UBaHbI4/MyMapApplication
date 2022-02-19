package softing.ubah4ukdev.mymapapplication.data.storage.entity

import androidx.room.Entity
import softing.ubah4ukdev.mymapapplication.domain.models.MarkerDomain

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.data.storage.entity
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
@Entity(
    tableName = "tab_map_markers",
    primaryKeys = ["markerId"]
)
data class Marker(
    val markerId: Int,
    val latitude: Double,
    val longitude: Double,
    val visible: Boolean = true,
    val title: String,
    val description: String
) {
    fun toDomain(): MarkerDomain =
        MarkerDomain(
            markerId = this.markerId,
            latitude = this.latitude,
            longitude = this.longitude,
            visible = this.visible,
            title = this.title,
            description = this.description
        )
}
