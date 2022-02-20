package softing.ubah4ukdev.mymapapplication.ui.markers.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import softing.ubah4ukdev.mymapapplication.databinding.MarkerItemBinding
import softing.ubah4ukdev.mymapapplication.domain.models.MarkerDomain
import softing.ubah4ukdev.mymapapplication.utils.extensions.click
import softing.ubah4ukdev.mymapapplication.utils.extensions.longClick

/**
 *   Project: MyMapApplication
 *
 *   Package: softing.ubah4ukdev.mymapapplication.ui.markers.adapter
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
class MarkerViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: MarkerItemBinding by viewBinding()

    fun bind(marker: MarkerDomain, delegate: MarkerAdapter.Delegate?) {
        with(viewBinding) {
            coordinate.text = marker.coordinateToString()
            title.text = marker.title
            description.text = marker.description
            root.click { delegate?.onItemClick(marker) }
            root.longClick {
                delegate?.onItemLongClick(marker)
                true
            }
        }
    }
}