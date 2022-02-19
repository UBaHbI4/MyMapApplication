package softing.ubah4ukdev.mymapapplication.ui.map

import android.os.Bundle
import android.util.Log
import android.view.View
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.runtime.image.ImageProvider
import softing.ubah4ukdev.mymapapplication.R
import softing.ubah4ukdev.mymapapplication.domain.AppState
import softing.ubah4ukdev.mymapapplication.domain.models.MarkersResult
import softing.ubah4ukdev.mymapapplication.domain.models.NewMarkerResult
import softing.ubah4ukdev.mymapapplication.ui.map.base.BaseMapFragment

class MapFragment : BaseMapFragment(), InputListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.mapView.map.addInputListener(this)

        viewModel.getOperationLiveData()
            .observe(viewLifecycleOwner) { res -> renderData(result = res) }

        viewModel.getMarkers()
    }

    private fun renderData(result: AppState) {
        when (result) {
            is AppState.Error -> {
                Log.d("VVVV", "${result.error.localizedMessage}")
            }
            AppState.Loading -> {
                Log.d("VVVV", "Loading")
            }
            is AppState.Success -> {
                Log.d("VVVV", "Success")
                renderSuccess(result)
            }
        }
    }

    private fun renderSuccess(result: AppState.Success) {
        when (val marker = result.data) {
            is NewMarkerResult -> {
                mapAddNewMarker(marker)
            }
            is MarkersResult -> {
                mapAddAllMarkers(marker)
            }
        }
    }

    /* Отрисовать все маркеры, имеющиеся в базе*/
    private fun mapAddAllMarkers(marker: MarkersResult) {
        marker.result.forEach {
            mapObjects?.addPlacemark(
                Point(it.latitude, it.longitude),
                ImageProvider.fromResource(
                    requireContext(),
                    R.drawable.ic_marker
                )
            )
        }
    }

    /* Отрисовать новый добавленный маркер*/
    private fun mapAddNewMarker(marker: NewMarkerResult) {
        mapObjects?.addPlacemark(
            Point(marker.position.latitude, marker.position.longitude),
            ImageProvider.fromResource(
                requireContext(),
                R.drawable.ic_marker
            )
        )
    }

    override fun onMapTap(p0: Map, p1: Point) {
        viewModel.saveMarker(p1)
    }

    override fun onMapLongTap(p0: Map, p1: Point) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding.mapView.map.removeInputListener(this)
    }
}